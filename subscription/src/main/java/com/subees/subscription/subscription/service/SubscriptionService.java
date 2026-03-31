package com.subees.subscription.subscription.service;

import com.subees.subscription.subscription.model.dto.CreateSubscriptionRequestdto;
import com.subees.subscription.subscription.model.dto.CreateSubscriptionResponsedto;
import com.subees.subscription.subscription.model.vo.Subscription;

import java.util.List;

public interface SubscriptionService {
    List<Subscription> getSubscriptions();

    CreateSubscriptionResponsedto createsubscriptiondto(CreateSubscriptionRequestdto requestdto);

    CreateSubscriptionResponsedto createSubscription(CreateSubscriptionRequestdto request);

}