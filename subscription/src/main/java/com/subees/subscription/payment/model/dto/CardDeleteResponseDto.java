package com.subees.subscription.payment.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CardDeleteResponseDto {

    private String message; // 카드 삭제 성공 여부 메시지
}
