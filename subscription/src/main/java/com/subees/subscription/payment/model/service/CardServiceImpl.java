package com.subees.subscription.payment.model.service;

import com.subees.subscription.payment.model.dto.CardCreateRequestDto;
import com.subees.subscription.payment.model.mapper.CardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
            int duplicateCount = cardMapper.countDuplicateByCardId(cardCreateRequestDto.getUserId(), cardCreateRequestDto.getCardId());
            if (duplicateCount > 0) {
                throw new IllegalArgumentException("이미 등록된 카드입니다.");
            }
            cardCreateRequestDto.setCustomCardCompany(null);
        }

        if (hasCustomCardCompany) {
            int duplicateCount = cardMapper.countDuplicateByCustomCardCompany(cardCreateRequestDto.getUserId(), cardCreateRequestDto.getCustomCardCompany());
            if (duplicateCount > 0) {
                throw new IllegalArgumentException("이미 등록된 카드입니다.");
            }
            cardCreateRequestDto.setCardId(null);
        }

        int result = cardMapper.insertPaymentMethod(cardCreateRequestDto);

        if (result != 1) {
            throw new RuntimeException("카드 등록 실패");
        }
    }
}
