package com.subees.submanager.subscription.service;

import com.subees.submanager.subscription.model.dto.CreateSubscriptionRequestdto;
import com.subees.submanager.subscription.model.dto.CreateSubscriptionResponsedto;
import com.subees.submanager.subscription.model.vo.Subscription;

import java.util.List;

public interface SubscriptionService {
    List<Subscription> getSubscriptions();

    CreateSubscriptionResponsedto createsubscriptiondto(CreateSubscriptionRequestdto requestdto);

    CreateSubscriptionResponsedto createSubscription(CreateSubscriptionRequestdto request);

}