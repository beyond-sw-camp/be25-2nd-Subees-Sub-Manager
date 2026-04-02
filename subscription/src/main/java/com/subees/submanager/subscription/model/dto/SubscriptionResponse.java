package com.subees.submanager.subscription.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class SubscriptionResponse {
    private Long subscriptionId;
    private Long itemId;
    private Long userId;
    private Long paymentId;
    private Integer price;
    private String billingCycle;
    private LocalDate paymentDate;
    private String useYn;
    private LocalDate createdAt;
    private LocalDate updatedAt;
}