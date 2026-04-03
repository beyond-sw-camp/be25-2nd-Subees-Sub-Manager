package com.subees.submanager.subscription.service;

import com.subees.submanager.subscription.model.dto.CreateSubscriptionRequest;
import com.subees.submanager.subscription.model.dto.CreateSubscriptionResponse;
import com.subees.submanager.subscription.model.dto.SubscriptionListResponse;
import com.subees.submanager.subscription.model.dto.SubscriptionResponse;
import com.subees.submanager.subscription.model.mapper.SubscriptionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {

    private final SubscriptionMapper subscriptionMapper;

    @Override
    public List<SubscriptionListResponse> getSubscriptions() {
        return subscriptionMapper.selectAll();
    }

    @Override
    public CreateSubscriptionResponse createSubscription(CreateSubscriptionRequest request) {

        if (request.getPrice() == null || request.getPrice() < 0) {
            throw new IllegalArgumentException("결제금액이 0원 이상이어야 합니다.");
        }

        if (request.getItemId() == null) {
            throw new IllegalArgumentException("항목을 선택하거나 직접 입력해 주세요.");
        }

        if (request.getStartDate() == null) {
            throw new IllegalArgumentException("결제 시작일은 필수입니다.");
        }

        if (request.getBillingCycle() == null) {
            throw new IllegalArgumentException("결제 주기를 선택해주세요.");
        }

        int duplicateCount = subscriptionMapper.countDuplicateSubscription(request);
        if (duplicateCount > 0) {
            throw new IllegalStateException("이미 등록된 구독항목 입니다.");
        }

        subscriptionMapper.insertSubscription(request);
        Long subscriptionId = subscriptionMapper.selectLastInsertedId();

        return CreateSubscriptionResponse.builder()
                .status("success")
                .data(CreateSubscriptionResponse.Data.builder()
                        .subscriptionId(subscriptionId)
                        .message("구독 항목이 성공적으로 등록되었습니다.")
                        .createdAt(LocalDate.now())
                        .build())
                .build();
    }

    @Override
    public SubscriptionResponse getSubscriptionById(Long subscriptionId) {
        SubscriptionResponse response = subscriptionMapper.selectById(subscriptionId);

        if (response == null) {
            throw new IllegalArgumentException("해당 구독 항목이 존재하지 않습니다.");
        }

        return response;
    }
}