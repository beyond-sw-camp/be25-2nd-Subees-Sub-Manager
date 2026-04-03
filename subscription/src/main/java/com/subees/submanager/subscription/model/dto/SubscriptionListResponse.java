package com.subees.submanager.subscription.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubscriptionListResponse {
    private Long subscriptionId;
    private String categoryName;
    private String itemName;
    private Integer price;
    private String useYn;
}