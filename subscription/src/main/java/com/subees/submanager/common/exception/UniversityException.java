package com.subees.submanager.common.exception;

import com.subees.submanager.common.exception.message.ExceptionMessage;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class UniversityException extends RuntimeException {
    private final String status;

    private final HttpStatus httpStatus;

    public UniversityException(ExceptionMessage message) {
        super(message.getMessage());

        this.status = message.name();
        this.httpStatus = message.getHttpStatus();
    }
}
