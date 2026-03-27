package com.subees.subscription.subscription.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateSubscriptionRequest {

    private Long itemId;
    private Long userId;
    private Long paymentId;
    private String subscriptionName;
    private Integer price;
    private String billingCycle;
    private String paymentDate;
    private String useYn;
}