package com.subees.subscription.payment.model.service;

import com.subees.subscription.payment.model.dto.CardCreateRequestDto;
import com.subees.subscription.payment.model.dto.CardUpdateRequestDto;

public interface CardService {
    void createCard(CardCreateRequestDto cardCreateRequestDto);

    void updateCard(CardUpdateRequestDto cardUpdateRequestDto);

    }
