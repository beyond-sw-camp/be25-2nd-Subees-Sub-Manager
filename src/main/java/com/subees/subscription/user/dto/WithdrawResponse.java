package com.subees.subscription.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class WithdrawResponse {

    private Long userId;
    private String userState;
    private String message;
}