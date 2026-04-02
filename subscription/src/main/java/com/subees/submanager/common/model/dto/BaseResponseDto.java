package com.subees.submanager.common.model.dto;

import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.List;

@Getter
@ToString
public class BaseResponseDto<T> {
    protected final int code;

    protected final String message;

    protected final List<T> items;

    public BaseResponseDto(HttpStatus httpStatus, T item) {
        this.code = httpStatus.value();
        this.message = httpStatus.getReasonPhrase();
        this.items = Collections.singletonList(item);
    }

    protected BaseResponseDto(HttpStatus httpStatus, List<T> items) {
        this.code = httpStatus.value();
        this.message = httpStatus.getReasonPhrase();
        this.items = items;
    }
}
