package com.subees.submanager.subscription.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class SubscriptionResponse {

    private Long subscriptionId;
    private String categoryName;
    private String itemName;
    private Integer price;
    private String billingCycle;
    private LocalDate paymentDate;
    private String useYn;
    private LocalDateTime createdAt;

    private PaymentMethod paymentMethod;

    @Getter
    @Setter
    public static class PaymentMethod {
        private Long id;
        private String name;
    }
}