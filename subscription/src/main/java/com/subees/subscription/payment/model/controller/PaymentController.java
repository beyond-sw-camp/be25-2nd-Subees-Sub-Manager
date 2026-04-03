package com.subees.subscription.payment.model.controller;

import com.subees.subscription.payment.model.dto.CardCreateResponseDto;
import com.subees.subscription.payment.model.dto.CardCreateRequestDto;
import com.subees.subscription.payment.model.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/subscriptions")
@RequiredArgsConstructor
public class PaymentController {
    private final CardService cardService;

    @PostMapping
    public CardCreateResponseDto getCardService(@RequestBody CardCreateRequestDto cardCreateRequestDto) {
        cardService.createCard(cardCreateRequestDto);
        return new CardCreateResponseDto("카드 등록 성공");
    }
}
