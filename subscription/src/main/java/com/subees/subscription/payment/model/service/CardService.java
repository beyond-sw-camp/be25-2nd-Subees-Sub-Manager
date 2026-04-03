package com.subees.subscription.payment.model.service;

import com.subees.subscription.payment.model.dto.CardCreateRequestDto;

public interface CardService {
    void createCard(CardCreateRequestDto cardCreateRequestDto);
}
