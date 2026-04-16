package com.subees.submanager.payment.model.service;

import com.subees.submanager.common.exception.UniversityException;
import com.subees.submanager.common.exception.message.ExceptionMessage;
import com.subees.submanager.payment.model.dto.CardCreateRequestDto;
import com.subees.submanager.payment.model.dto.CardCreateResponseDto;
import com.subees.submanager.payment.model.dto.CardResponseDto;
import com.subees.submanager.payment.model.dto.CardUpdateRequestDto;
import com.subees.submanager.payment.model.mapper.CardMapper;
import com.subees.submanager.payment.model.vo.Payment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {

    private final CardMapper cardMapper;

    /*
     * 카드 등록
     * - 비로그인 시 등록 예외처리
     * - 카드 선택, 직접입력 둘 다 입력 or 둘 다 입력 X 예외처리
     * - 같은 카드 아이디 or 직접입력 카드, 별칭 등록할 시 예외처리
     */
    @Override
    public CardCreateResponseDto createCard(CardCreateRequestDto cardCreateRequestDto) {

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
                    cardCreateRequestDto.getCardName(),
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
            throw new UniversityException(ExceptionMessage.CARD_CREATE_FAILED);
        }

        return new CardCreateResponseDto(
                payment.getPaymentId(),
                payment.getCardName(),
                LocalDate.now()
        );
    }
    /*
     * 카드 수정
     * - 결제수단 ID,  사용자 ID, 카드 별칭X 예외처리
     * - 카드 선택 방식과 직접입력 방식 중 하나만 입력 가능
     * - 수정 중인 카드가 기존 카드와 중복 시(카드ID,직접입력카드,별칭,페이먼트ID) 예외처리
     */
    @Override
    public void updateCard(CardUpdateRequestDto cardUpdateRequestDto) {

        if (cardUpdateRequestDto.getPaymentId() == null) {
            throw new UniversityException(ExceptionMessage.PAYMENT_ID_REQUIRED);
        }

        if (cardUpdateRequestDto.getUserId() == null) {
            throw new UniversityException(ExceptionMessage.FORBIDDEN);
        }

        if (cardUpdateRequestDto.getCardName() == null || cardUpdateRequestDto.getCardName().trim().isEmpty()) {
            throw new UniversityException(ExceptionMessage.CARD_NAME_REQUIRED);
        }

        boolean hasCardId = cardUpdateRequestDto.getCardId() != null; // 선택 카드 여부
        boolean hasCustomCardCompany = cardUpdateRequestDto.getCustomCardCompany() != null // 직접 입력 카드 여부
                && !cardUpdateRequestDto.getCustomCardCompany().trim().isEmpty();

        if ((!hasCardId && !hasCustomCardCompany) || (hasCardId && hasCustomCardCompany)) {
            throw new UniversityException(ExceptionMessage.CARD_INPUT_INVALID);
        }

        Payment existingPayment = cardMapper.selectPaymentById(cardUpdateRequestDto.getPaymentId()); // 수정 전 원본 데이터

        // 수정 카드 존재 여부
        if (existingPayment == null) {
            throw new UniversityException(ExceptionMessage.PAYMENT_METHOD_NOT_FOUND);
        }

        // 수정 전, 수정할 유저 아이디 일치 여부
        if (existingPayment.getUserId() != cardUpdateRequestDto.getUserId()) {
            throw new UniversityException(ExceptionMessage.CARD_ACCESS_DENIED);
        }

        // 선택 방식 & 직접 입력 방식 카드 수정 전, 후  값 비교
        if (hasCardId) {
            boolean sameAsCurrent =
                    existingPayment.getCardId() != null
                            && existingPayment.getCardId().equals(cardUpdateRequestDto.getCardId())
                            && existingPayment.getCardName().equals(cardUpdateRequestDto.getCardName());

            if (sameAsCurrent) {
                throw new UniversityException(ExceptionMessage.DUPLICATE_CARD);
            }

            int duplicateCount = cardMapper.duplicateSelectedCard(
                    cardUpdateRequestDto.getUserId(),
                    cardUpdateRequestDto.getCardId(),
                    cardUpdateRequestDto.getCardName(),
                    cardUpdateRequestDto.getPaymentId()
            );

            if (duplicateCount > 0) {
                throw new UniversityException(ExceptionMessage.DUPLICATE_CARD);
            }
        }

        if (hasCustomCardCompany) {
            boolean sameAsCurrent =
                    existingPayment.getCustomCardCompany() != null
                            && existingPayment.getCustomCardCompany().equals(cardUpdateRequestDto.getCustomCardCompany())
                            && existingPayment.getCardName().equals(cardUpdateRequestDto.getCardName());

            if (sameAsCurrent) {
                throw new UniversityException(ExceptionMessage.DUPLICATE_CARD_NAME);
            }

            int duplicateCount = cardMapper.duplicateCustomCard(
                    cardUpdateRequestDto.getUserId(),
                    cardUpdateRequestDto.getCustomCardCompany(),
                    cardUpdateRequestDto.getCardName(),
                    cardUpdateRequestDto.getPaymentId()
            );

            if (duplicateCount > 0) {
                throw new UniversityException(ExceptionMessage.DUPLICATE_CARD);
            }
        }

        Payment updatePayment = new Payment();
        updatePayment.setPaymentId(cardUpdateRequestDto.getPaymentId());
        updatePayment.setUserId(cardUpdateRequestDto.getUserId());
        updatePayment.setCardId(hasCardId ? cardUpdateRequestDto.getCardId() : null);
        updatePayment.setCustomCardCompany(hasCustomCardCompany ? cardUpdateRequestDto.getCustomCardCompany() : null);
        updatePayment.setCardName(cardUpdateRequestDto.getCardName());

        int updateCount = cardMapper.updateCard(updatePayment);

        if (updateCount == 0) {
            throw new UniversityException(ExceptionMessage.CARD_UPDATE_FAILED);
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

    @Override
    public List<CardResponseDto> getCards(Long userId) {


        List<Payment> paymentList = cardMapper.selectCards(userId);

        return paymentList.stream()
                .map(payment -> new CardResponseDto(
                        payment.getPaymentId(),
                        payment.getCardId(),
                        payment.getCardName(),
                        payment.getCustomCardCompany(),
                        payment.getIsActive()
                ))
                .toList();
    }


}