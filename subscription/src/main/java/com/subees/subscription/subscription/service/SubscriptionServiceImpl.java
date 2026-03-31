package com.subees.subscription.subscription.service;

import com.subees.subscription.subscription.model.dto.CreateSubscriptionRequestdto;
import com.subees.subscription.subscription.model.dto.CreateSubscriptionResponsedto;
import com.subees.subscription.subscription.model.mapper.SubscriptionMapper;
import com.subees.subscription.subscription.model.vo.Subscription;
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
    public CreateSubscriptionResponsedto createsubscriptiondto(CreateSubscriptionRequestdto requestdto) {
        if (requestdto.getPrice() == null || requestdto.getPrice() < 0) {
            throw new IllegalArgumentException("결제금액이 0원 이상이어야 합니다.");
        }

        if (requestdto.getItemId() == null) {
            throw new IllegalArgumentException("항목을 선택하거나 직접 입력해 주세요.");
        }

        int duplicateCount = subscriptionMapper.countDuplicateSubscription(requestdto);
        if (duplicateCount > 0) {
            throw new IllegalStateException("이미 등록된 구독항목 입니다.");
        }

        subscriptionMapper.insertSubscription(requestdto);
        Long subscriptionId = subscriptionMapper.selectLastInsertedId();

        return CreateSubscriptionResponsedto.builder()
                .status("success")
                .data(CreateSubscriptionResponsedto.Data.builder()
                        .subscriptionId(subscriptionId)
                        .message("구독 항목이 성공적으로 등록되었습니다.")
                        .createdAt(LocalDate.now())
                        .build())
                .build();
    }

    @Override
    public CreateSubscriptionResponsedto createSubscription(CreateSubscriptionRequestdto request) {
        return null;
    }
}