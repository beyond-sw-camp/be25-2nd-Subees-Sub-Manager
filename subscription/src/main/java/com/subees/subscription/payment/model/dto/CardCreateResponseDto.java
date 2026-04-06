package com.subees.subscription.payment.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CardCreateResponseDto{
    private String message; // 카드 등록 성공 여부 메시지
}
