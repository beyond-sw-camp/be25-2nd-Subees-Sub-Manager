package com.subees.backend.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LogoutResponse {

    private Long userId;
    private String message;
}