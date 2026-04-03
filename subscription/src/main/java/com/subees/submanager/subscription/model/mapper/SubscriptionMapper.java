package com.subees.submanager.subscription.model.mapper;

import com.subees.submanager.subscription.model.dto.CreateSubscriptionRequest;
import com.subees.submanager.subscription.model.dto.SubscriptionListResponse;
import com.subees.submanager.subscription.model.dto.SubscriptionResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SubscriptionMapper {
    List<SubscriptionListResponse> selectAll();

    int countDuplicateSubscription(CreateSubscriptionRequest request);

    void insertSubscription(CreateSubscriptionRequest request);

    Long selectLastInsertedId();

    SubscriptionResponse selectById(Long subscriptionId);
}