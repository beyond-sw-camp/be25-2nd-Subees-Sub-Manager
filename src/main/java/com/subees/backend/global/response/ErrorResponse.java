package com.subees.backend.global.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorResponse {

    private boolean success;
    private int status;
    private String message;
}