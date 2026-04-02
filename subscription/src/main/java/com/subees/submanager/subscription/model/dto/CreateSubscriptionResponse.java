package com.subees.submanager.subscription.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@Builder
public class CreateSubscriptionResponse {

    private String status;
    private Data data;

    @Getter
    @AllArgsConstructor
    @Builder
    public static class Data {
        private Long subscriptionId;
        private String message;
        private LocalDate createdAt;
    }
}