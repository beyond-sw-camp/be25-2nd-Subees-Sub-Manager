package com.subees.subscription.payment.model.controller;

import com.subees.subscription.payment.model.dto.CardCreateResponseDto;
import com.subees.subscription.payment.model.dto.CardCreateRequestDto;
import com.subees.subscription.payment.model.dto.CardDeleteResponseDto;
import com.subees.subscription.payment.model.dto.CardUpdateRequestDto;
import com.subees.subscription.payment.model.dto.CardUpdateResponseDto;
import com.subees.subscription.payment.model.service.CardService;
import lombok.RequiredArgsConstructor;
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

    @PostMapping
    public CardCreateResponseDto getCardService(@RequestBody CardCreateRequestDto cardCreateRequestDto) {
        cardService.createCard(cardCreateRequestDto);
        return new CardCreateResponseDto("카드 등록을 완료하였습니다.");
    }

    @PatchMapping("/{paymentId}")
    public CardUpdateResponseDto updateCardName(
            @PathVariable Long paymentId,
            @RequestBody CardUpdateRequestDto cardUpdateRequestDto
    ) {
        cardUpdateRequestDto.setPaymentId(paymentId);
        cardService.updateCard(cardUpdateRequestDto);
        return new CardUpdateResponseDto("카드 별칭 수정을 완료하였습니다.");
    }
    @DeleteMapping("/{paymentId}")
    public CardDeleteResponseDto deleteCard(
            @PathVariable Long paymentId,
            @RequestParam Long userId
    ) {
        cardService.deleteCard(paymentId, userId);
        return new CardDeleteResponseDto("카드를 삭제하였습니다.");
    }
}
