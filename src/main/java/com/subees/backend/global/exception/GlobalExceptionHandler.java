package com.subees.backend.global.exception;

import com.subees.backend.global.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse handleValidationException(MethodArgumentNotValidException e) {
        String message = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .findFirst()
                .map(fieldError -> fieldError.getDefaultMessage())
                .orElse("잘못된 요청입니다.");

        return new ErrorResponse(false, 400, message);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ErrorResponse handleIllegalArgumentException(IllegalArgumentException e) {
        return new ErrorResponse(false, 400, e.getMessage());
    }

    @ExceptionHandler(IllegalStateException.class)
    public ErrorResponse handleIllegalStateException(IllegalStateException e) {
        return new ErrorResponse(false, 400, e.getMessage());
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ErrorResponse handleResponseStatusException(ResponseStatusException e) {
        return new ErrorResponse(
                false,
                e.getStatusCode().value(),
                e.getReason() != null ? e.getReason() : "요청 처리 중 오류가 발생했습니다."
        );
    }

    @ExceptionHandler(Exception.class)
    public ErrorResponse handleException(Exception e) {
        return new ErrorResponse(false, 500, "서버 내부 오류가 발생했습니다.");
    }
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ErrorResponse handleMaxUploadSizeExceededException(MaxUploadSizeExceededException e) {
        return new ErrorResponse(false, 400, "파일 크기는 5MB를 초과할 수 없습니다.");
    }
}