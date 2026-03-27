package com.subees.subscription.subscription.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class SubscriptionResponse {

    private Long subscriptionId;
    private Long itemId;
    private Long userId;
    private Long paymentId;
    private String subscriptionName;
    private Integer price;
    private String billingCycle;
    private String paymentDate;
    private String useYn;
}