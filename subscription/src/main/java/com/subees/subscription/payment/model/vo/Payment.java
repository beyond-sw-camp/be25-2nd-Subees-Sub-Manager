package com.subees.subscription.payment.model.vo;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
public class Payment {
    private Long cardId;

    public String cardName;

    private String cardCompany;

    private Long paymentId;

    private long userId;

    private char isActive;

    private LocalDate created_at;

    private String customCardCompany;
}
