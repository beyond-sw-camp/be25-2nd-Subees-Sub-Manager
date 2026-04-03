package com.subees.subscription.common.exception.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ExceptionMessage {
    USER_NOT_FOUND("존재하지 않는 사용자입니다.", HttpStatus.NOT_FOUND),
    DUPLICATE_EMAIL("이미 가입된 이메일입니다.", HttpStatus.BAD_REQUEST),
    INVALID_PASSWORD("비밀번호가 일치하지 않습니다.", HttpStatus.UNAUTHORIZED),
    SUBSCRIPTION_NOT_FOUND("구독 정보를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    CARD_NOT_FOUND("등록된 카드 정보를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    POST_NOT_FOUND("해당 게시글을 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    INTERNAL_SERVER_ERROR("서버 내부 오류가 발생했습니다.", HttpStatus.INTERNAL_SERVER_ERROR),
    // 추가 SY
    INVALID_PAGE("유효하지 않은 페이지입니다.", HttpStatus.BAD_REQUEST), // 없는 페이지를 요청했을 시
    INVALID_REQUEST("유효하지 않은 요청입니다.", HttpStatus.BAD_REQUEST), // title, content 누락 또는 요청 바디가 비어 있거나 JSON형식이 잘못 됐을 시
    UNAUTHORIZED("로그인이 필요한 서비스입니다.", HttpStatus.UNAUTHORIZED),
    FORBIDDEN("게시글을 수정할 권한이 없습니다.", HttpStatus.FORBIDDEN);

    private final String message;

    private final HttpStatus httpStatus;
}
