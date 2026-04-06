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

    // 카드(등록, 수정 , 삭제) 관련 예외 메시지
    CARD_INPUT_INVALID("카드 선택 또는 직접 입력 중 하나만 입력해야 합니다.", HttpStatus.BAD_REQUEST),
    DUPLICATE_CARD("이미 등록된 카드입니다.", HttpStatus.CONFLICT),
    CARD_ALREADY_DELETED("이미 삭제된 카드입니다.", HttpStatus.CONFLICT),
    PAYMENT_METHOD_NOT_FOUND("해당 결제수단이 존재하지 않거나 수정 권한이 없습니다.", HttpStatus.NOT_FOUND),
    CARD_IN_USE("현재 사용 중인 카드는 삭제할 수 없습니다.", HttpStatus.BAD_REQUEST),
    CARD_ACCESS_DENIED("해당 카드에 대한 접근 권한이 없습니다.", HttpStatus.FORBIDDEN),
    CARD_NAME_REQUIRED("카드 별칭은 필수입니다.", HttpStatus.BAD_REQUEST),
    PAYMENT_ID_REQUIRED("결제수단 아이디는 필수입니다.", HttpStatus.BAD_REQUEST),
    CARD_CREATE_FAILED("카드 등록에 실패했습니다.", HttpStatus.INTERNAL_SERVER_ERROR),
    CARD_UPDATE_FAILED("카드 수정에 실패했습니다.", HttpStatus.INTERNAL_SERVER_ERROR),
    CARD_DELETE_FAILED("카드 삭제에 실패했습니다.", HttpStatus.INTERNAL_SERVER_ERROR),
    CARD_DELETE_NOT_FOUND("삭제할 카드가 없거나 이미 비활성화된 카드입니다.", HttpStatus.NOT_FOUND);



    private final String message;

    private final HttpStatus httpStatus;
}
