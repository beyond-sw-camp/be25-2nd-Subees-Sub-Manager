package com.subees.submanager.subscription.service;

import com.subees.submanager.subscription.model.dto.CreateSubscriptionRequest;
import com.subees.submanager.subscription.model.dto.CreateSubscriptionResponse;
import com.subees.submanager.subscription.model.dto.SubscriptionResponse;

import java.util.List;

public interface SubscriptionService {
    List<SubscriptionResponse> getSubscriptions();

    CreateSubscriptionResponse createSubscription(CreateSubscriptionRequest request);
}