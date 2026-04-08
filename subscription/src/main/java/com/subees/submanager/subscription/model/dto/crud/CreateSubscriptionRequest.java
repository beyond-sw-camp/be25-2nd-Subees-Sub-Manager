package com.subees.submanager.subscription.model.dto.crud;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class CreateSubscriptionRequest {
    private Long categoryId;
    private Long itemId;
    private Long paymentId;

    private Integer price;
    private String billingCycle;

    private LocalDate startDate;
}