package com.subees.subscription.payment.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CardCreateRequestDto {
    private Long userId;

    private Long cardId;

    private String customCardCompany;

    private String cardName;


}
