package com.subees.subscription.subscription.service;

import com.subees.subscription.subscription.mapper.SubscriptionMapper;
import com.subees.subscription.subscription.model.dto.CreateSubscriptionRequest;
import com.subees.subscription.subscription.model.dto.UpdateSubscriptionRequest;
import com.subees.subscription.subscription.model.dto.UpdateSubscriptionStatusRequest;
import com.subees.subscription.subscription.model.vo.Subscription;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SubscriptionService {

    private final SubscriptionMapper subscriptionMapper;

    public void createSubscription(CreateSubscriptionRequest request) {
        Subscription subscription = Subscription.builder()
                .itemId(request.getItemId())
                .userId(request.getUserId())
                .paymentId(request.getPaymentId())
                .subscriptionName(request.getSubscriptionName())
                .price(request.getPrice())
                .billingCycle(request.getBillingCycle())
                .paymentDate(LocalDateTime.parse(request.getPaymentDate()))
                .useYn(request.getUseYn())
                .build();

        subscriptionMapper.insertSubscription(subscription);
    }

    public List<Subscription> getSubscriptions() {
        return subscriptionMapper.findAllSubscriptions();
    }

    public Subscription getSubscription(Long subscriptionId) {
        return subscriptionMapper.findSubscriptionById(subscriptionId);
    }

    public void updateSubscription(Long subscriptionId, UpdateSubscriptionRequest request) {
        Subscription subscription = Subscription.builder()
                .subscriptionId(subscriptionId)
                .itemId(request.getItemId())
                .paymentId(request.getPaymentId())
                .subscriptionName(request.getSubscriptionName())
                .price(request.getPrice())
                .billingCycle(request.getBillingCycle())
                .paymentDate(LocalDateTime.parse(request.getPaymentDate()))
                .useYn(request.getUseYn())
                .build();

        subscriptionMapper.updateSubscription(subscription);
    }

    public void deleteSubscription(Long subscriptionId) {
        subscriptionMapper.deleteSubscription(subscriptionId);
    }

    public void updateSubscriptionStatus(Long subscriptionId, UpdateSubscriptionStatusRequest request) {
        subscriptionMapper.updateSubscriptionStatus(subscriptionId, request.getUseYn());
    }
}