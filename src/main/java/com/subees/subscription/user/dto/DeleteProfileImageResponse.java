package com.subees.subscription.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DeleteProfileImageResponse {
    private Long userId;
    private String profileImageUrl;
    private String message;
}