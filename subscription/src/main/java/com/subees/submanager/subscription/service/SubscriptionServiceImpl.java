package com.subees.submanager.subscription.service;

import com.subees.submanager.subscription.model.dto.CreateSubscriptionRequest;
import com.subees.submanager.subscription.model.dto.CreateSubscriptionResponse;
import com.subees.submanager.subscription.model.mapper.SubscriptionMapper;
import com.subees.submanager.subscription.model.vo.Subscription;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {
    private final SubscriptionMapper subscriptionMapper;

    @Override
    public List<Subscription> getSubscriptions() {
        return subscriptionMapper.selectAll();
    }

    @Override
    public CreateSubscriptionResponse createSubscription(CreateSubscriptionRequest request) {

        // 1. 유효성 검사
        if (request.getPrice() == null || request.getPrice() < 0) {
            throw new IllegalArgumentException("결제금액이 0원 이상이어야 합니다.");
        }

        if (request.getItemId() == null) {
            throw new IllegalArgumentException("항목을 선택하거나 직접 입력해 주세요.");
        }

        // 2. 중복 검사
        int duplicateCount = subscriptionMapper.countDuplicateSubscription(request);
        if (duplicateCount > 0) {
            throw new IllegalStateException("이미 등록된 구독항목 입니다.");
        }

        // 3. insert
        subscriptionMapper.insertSubscription(request);

        Long subscriptionId = subscriptionMapper.selectLastInsertedId();

        // 4. 응답
        return CreateSubscriptionResponse.builder()
                .status("success")
                .data(CreateSubscriptionResponse.Data.builder()
                        .subscriptionId(subscriptionId)
                        .message("구독 항목이 성공적으로 등록되었습니다.")
                        .createdAt(LocalDate.now())
                        .build())
                .build();
    }

}