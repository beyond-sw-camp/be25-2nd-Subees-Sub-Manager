package com.subees.backend.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdateProfileResponse {

    private Long userId;
    private String email;
    private String nickname;
    private String message;
}