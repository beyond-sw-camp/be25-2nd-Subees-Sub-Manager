package com.subees.subscription.payment.model.service;

import com.subees.subscription.consumption.model.dto.CalendarSummaryResponseDto;
import com.subees.subscription.payment.model.dto.CardCreateRequestDto;
import com.subees.subscription.payment.model.dto.CardUpdateRequestDto;
import com.subees.subscription.payment.model.mapper.CardMapper;
import com.subees.subscription.payment.model.vo.Payment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/*
 * 카드 등록 로직
 * - userId 필수 여부 검증
 * - 카드 선택(cardId) 또는 직접 입력(customCardCompany) 중 하나 입력 검증
 * - 선택 카드 / 직접 입력 카드 각각 중복 등록 여부 확인
 * - 사용하지 않는 값은 null 처리 후 payment_method 테이블에 저장
 * - insert 결과가 1이 아니면 등록 실패 예외 발생
 */

@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {

    private final CardMapper cardMapper;

    @Override
    public void createCard(CardCreateRequestDto cardCreateRequestDto) {

        if (cardCreateRequestDto.getUserId() == null) {
            throw new IllegalArgumentException("사용자 아이디는 필수입니다.");
        }

        boolean hasCardId = cardCreateRequestDto.getCardId() != null;
        boolean hasCustomCardCompany = cardCreateRequestDto.getCustomCardCompany() != null
                && !cardCreateRequestDto.getCustomCardCompany().trim().isEmpty();

        if ((!hasCardId && !hasCustomCardCompany) || (hasCardId && hasCustomCardCompany)) {
            throw new IllegalArgumentException("카드 선택 또는 직접 입력 중 하나만 입력해야 합니다.");
        }

        if (hasCardId) {
            int duplicateCount = cardMapper.countDuplicateByCardId(
                    cardCreateRequestDto.getUserId(),
                    cardCreateRequestDto.getCardId()
            );
            if (duplicateCount > 0) {
                throw new IllegalArgumentException("이미 등록된 카드입니다.");
            }
        }

        if (hasCustomCardCompany) {
            int duplicateCount = cardMapper.countDuplicateByCustomCardCompany(
                    cardCreateRequestDto.getUserId(),
                    cardCreateRequestDto.getCustomCardCompany()
            );
            if (duplicateCount > 0) {
                throw new IllegalArgumentException("이미 등록된 카드입니다.");
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

    @Override
    public void updateCard(CardUpdateRequestDto cardUpdateRequestDto) {

        if (cardUpdateRequestDto.getPaymentId() == null) {
            throw new IllegalArgumentException("결제수단 아이디는 필수입니다.");
        }

        if (cardUpdateRequestDto.getUserId() == null) {
            throw new IllegalArgumentException("사용자 아이디는 필수입니다.");
        }

        if (cardUpdateRequestDto.getCardName() == null || cardUpdateRequestDto.getCardName().trim().isEmpty()) {
            throw new IllegalArgumentException("카드 별칭은 필수입니다.");
        }

        Payment payment = new Payment();
        payment.setPaymentId(cardUpdateRequestDto.getPaymentId());
        payment.setUserId(cardUpdateRequestDto.getUserId());
        payment.setCardName(cardUpdateRequestDto.getCardName());

        int result = cardMapper.updateCard(payment);

        if (result != 1) {
            throw new RuntimeException("카드 수정 실패");
        }
    }
}






