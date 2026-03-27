package com.subees.subscription.subscription.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Subscription {

    private Long subscriptionId;
    private Long itemId;
    private Long userId;
    private Long paymentId;
    private String subscriptionName;
    private Integer price;
    private String billingCycle;
    private LocalDateTime paymentDate;
    private String useYn;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}