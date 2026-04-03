package com.subees.subscription.common.exception.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ExceptionMessage {
    USER_NOT_FOUND("존재하지 않는 사용자입니다.", HttpStatus.NOT_FOUND),
    DUPLICATE_EMAIL("이미 사용 중인 이메일입니다.", HttpStatus.BAD_REQUEST),
    DUPLICATE_NICKNAME("이미 사용 중인 닉네임입니다.", HttpStatus.BAD_REQUEST),
    INVALID_EMAIL("가입되지 않은 이메일입니다.", HttpStatus.NOT_FOUND),
    INVALID_PASSWORD("비밀번호가 일치하지 않습니다.", HttpStatus.UNAUTHORIZED),
    INVALID_CURRENT_PASSWORD("현재 비밀번호가 일치하지 않습니다.", HttpStatus.BAD_REQUEST),
    SAME_AS_OLD_PASSWORD("새 비밀번호는 현재 비밀번호와 달라야 합니다.", HttpStatus.BAD_REQUEST),
    WITHDRAWN_USER("탈퇴한 계정입니다.", HttpStatus.BAD_REQUEST),
    INACTIVE_USER("비활성화된 계정입니다.", HttpStatus.BAD_REQUEST),
    ALREADY_WITHDRAWN_USER("이미 탈퇴 처리된 계정입니다.", HttpStatus.BAD_REQUEST),
    EMPTY_UPLOAD_FILE("업로드할 파일이 없습니다.", HttpStatus.BAD_REQUEST),
    INVALID_IMAGE_FILE("이미지 파일만 업로드할 수 있습니다.", HttpStatus.BAD_REQUEST),
    INVALID_FILE_EXTENSION("허용되지 않는 파일 확장자입니다.", HttpStatus.BAD_REQUEST),
    FILE_UPLOAD_FAILED("파일 저장 중 오류가 발생했습니다.", HttpStatus.INTERNAL_SERVER_ERROR),
    FORBIDDEN("접근 권한이 없습니다.", HttpStatus.FORBIDDEN),
    UNAUTHORIZED("인증이 필요합니다.", HttpStatus.UNAUTHORIZED),
    INTERNAL_SERVER_ERROR("서버 내부 오류가 발생했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);

    private final String message;
    private final HttpStatus httpStatus;
}
