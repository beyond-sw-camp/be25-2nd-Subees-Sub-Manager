package com.subees.subscription.payment.model.dto;

import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Getter
@Setter
public class CardUpdateRequestDto {
    @NotNull(message = "사용자 아이디는 필수입니다.")
    private Long userId;

    @NotBlank(message = "카드 별칭은 필수입니다.")
    private String cardName;

    private Long paymentId;
}
