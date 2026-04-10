package com.subees.submanager.payment.controller;

import com.subees.submanager.common.model.dto.BaseResponseDto;
import com.subees.submanager.payment.model.dto.CardCreateRequestDto;
import com.subees.submanager.payment.model.dto.CardUpdateRequestDto;
import com.subees.submanager.payment.model.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/cards")
@RequiredArgsConstructor
public class PaymentController {
    private final CardService cardService;

    // 카드 등록
    @PostMapping
    public ResponseEntity<BaseResponseDto<String>> createCard(@RequestBody CardCreateRequestDto cardCreateRequestDto) {
        cardService.createCard(cardCreateRequestDto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new BaseResponseDto<>(HttpStatus.CREATED, "카드 등록을 완료하였습니다."));
    }

    // 카드 수정
    @PatchMapping("/{paymentId}")
    public ResponseEntity<BaseResponseDto<String>> updateCard(
            @PathVariable Long paymentId,
            @RequestBody CardUpdateRequestDto requestDto
    ) {
        requestDto.setPaymentId(paymentId);
        cardService.updateCard(requestDto);

        return ResponseEntity.ok(new BaseResponseDto<>(HttpStatus.OK, "카드 수정 성공"));
    }

    // 카드 삭제
    @DeleteMapping("/{paymentId}")
    public ResponseEntity<BaseResponseDto<String>> deleteCard(
            @PathVariable Long paymentId,
            @RequestParam Long userId
    ) {
        cardService.deleteCard(paymentId, userId);

        return ResponseEntity.ok(new BaseResponseDto<>(HttpStatus.OK, "카드를 삭제하였습니다."));
    }
}
