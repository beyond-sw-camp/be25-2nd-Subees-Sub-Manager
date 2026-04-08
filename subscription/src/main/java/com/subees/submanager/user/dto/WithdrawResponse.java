package com.subees.submanager.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class WithdrawResponse {

    private Long userId;
    private String userState;
    private String message;
}