package com.subees.subscription.payment.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CardUpdateRequestDto {

    private Long userId;

    private String cardName;

    private Long paymentId;
}
