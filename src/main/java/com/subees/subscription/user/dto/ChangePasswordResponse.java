package com.subees.subscription.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ChangePasswordResponse {

    private Long userId;
    private String message;
}