package com.subees.backend.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CheckNicknameResponse {
    private boolean available;
    private String message;
}