package com.subees.submanager.subscription.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class CreateSubscriptionRequestdto {

    private Long categoryId;

    @JsonProperty("item_id")
    private Long itemId;

    @JsonProperty("payment_id")
    private Long paymentId;

    private Integer price;

    @JsonProperty("billing_cycle")
    private String billingCycle;

    private LocalDate paymentDate;
}