package com.subees.submanager.payment.model.service;

import com.subees.submanager.payment.model.dto.CardCreateRequestDto;
import com.subees.submanager.payment.model.dto.CardUpdateRequestDto;

public interface CardService {
    void createCard(CardCreateRequestDto cardCreateRequestDto);

    void updateCard(CardUpdateRequestDto cardUpdateRequestDto);

    void deleteCard(Long paymentId, Long userId);

    }
