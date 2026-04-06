package com.subees.subscription.payment.model.service;

import com.subees.subscription.common.exception.UniversityException;
import com.subees.subscription.common.exception.message.ExceptionMessage;
import com.subees.subscription.payment.model.dto.CardCreateRequestDto;
import com.subees.subscription.payment.model.dto.CardUpdateRequestDto;
import com.subees.subscription.payment.model.mapper.CardMapper;
import com.subees.subscription.payment.model.vo.Payment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/*
 * 카드 등록
 */
@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {

    private final CardMapper cardMapper;

    @Override
    public void createCard(CardCreateRequestDto cardCreateRequestDto) {

        if (cardCreateRequestDto.getUserId() == null) {
            throw new IllegalArgumentException("로그인이 필요합니다.");
        }

        boolean hasCardId = cardCreateRequestDto.getCardId() != null;
        boolean hasCustomCardCompany = cardCreateRequestDto.getCustomCardCompany() != null
                && !cardCreateRequestDto.getCustomCardCompany().trim().isEmpty();

        if ((!hasCardId && !hasCustomCardCompany) || (hasCardId && hasCustomCardCompany)) {
            throw new UniversityException(ExceptionMessage.CARD_INPUT_INVALID);
        }

        if (hasCardId) {
            int duplicateCount = cardMapper.countCardId(
                    cardCreateRequestDto.getUserId(),
                    cardCreateRequestDto.getCardId(),
                    cardCreateRequestDto.getCardName()
            );
            if (duplicateCount > 0) {
                throw new UniversityException(ExceptionMessage.DUPLICATE_CARD);
            }
        }

        if (hasCustomCardCompany) {
            int duplicateCount = cardMapper.countCardCustom(
                    cardCreateRequestDto.getUserId(),
                    cardCreateRequestDto.getCustomCardCompany(),
                    cardCreateRequestDto.getIsActive()
            );
            if (duplicateCount > 0) {
                throw new UniversityException(ExceptionMessage.DUPLICATE_CARD);
            }
        }

        Payment payment = new Payment();
        payment.setUserId(cardCreateRequestDto.getUserId());
        payment.setCardId(hasCardId ? cardCreateRequestDto.getCardId() : null);
        payment.setCustomCardCompany(hasCustomCardCompany ? cardCreateRequestDto.getCustomCardCompany() : null);
        payment.setCardName(cardCreateRequestDto.getCardName());

        int result = cardMapper.insertPaymentMethod(payment);

        if (result != 1) {
            throw new RuntimeException("카드 등록 실패");
        }
    }

    /*
    * 카드 수정
    */

    @Override
    public void updateCard(CardUpdateRequestDto cardUpdateRequestDto) {

       // 결제수단 ID가 없으면 어떤 카드를 수정 불가일 시 예외처리
        if (cardUpdateRequestDto.getPaymentId() == null) {
            throw new UniversityException(ExceptionMessage.PAYMENT_ID_REQUIRED);
        }

        // 사용자 ID가 없으면 본인인지 확인 불가일 시 예외처리
        if (cardUpdateRequestDto.getUserId() == null) {
            throw new IllegalArgumentException("사용자 아이디는 필수입니다.");
        }

        // 카드 수정 시 별칭 수정 필수 -> 공백일 시 예외처리
        if (cardUpdateRequestDto.getCardName() == null || cardUpdateRequestDto.getCardName().trim().isEmpty()) {
            throw new UniversityException(ExceptionMessage.CARD_NAME_REQUIRED);
        }

        Payment payment = new Payment();
        payment.setPaymentId(cardUpdateRequestDto.getPaymentId());
        payment.setUserId(cardUpdateRequestDto.getUserId());
        payment.setCardName(cardUpdateRequestDto.getCardName());

        // 결제수단 Id, 사용자 Id 일치 시 수정
        int updateCount = cardMapper.updateCard(payment);

        // 수정된 행이 없으면 존재하지 않는 카드 혹은 수정 권한이 없는 경우 예외 처리
        if (updateCount == 0) {
            throw new UniversityException(ExceptionMessage.PAYMENT_METHOD_NOT_FOUND);
        }
    }

    /*
    * 카드 삭제
     */
    @Override
    public void deleteCard(Long paymentId, Long userId) {

        if (userId == null) {
            throw new UniversityException(ExceptionMessage.PAYMENT_METHOD_NOT_FOUND);
        }

        if (paymentId == null) {
            throw new UniversityException(ExceptionMessage.PAYMENT_ID_REQUIRED);
        }

        Payment payment = cardMapper.selectPaymentById(paymentId);

        if (payment == null) {
            throw new UniversityException(ExceptionMessage.PAYMENT_METHOD_NOT_FOUND);
        }

        if (payment.getUserId() != userId) {
            throw new UniversityException(ExceptionMessage.CARD_ACCESS_DENIED);
        }

        if (payment.getIsActive() == '0') {
            throw new UniversityException(ExceptionMessage.CARD_ALREADY_DELETED);
        }

        int usingCount = cardMapper.activeCardByPayment(paymentId);
        if (usingCount > 0) {
            throw new UniversityException(ExceptionMessage.CARD_IN_USE);
        }

        int result = cardMapper.deleteCard(userId, paymentId);

        if (result != 1) {
            throw new UniversityException(ExceptionMessage.CARD_DELETE_FAILED);
        }
    }
}






