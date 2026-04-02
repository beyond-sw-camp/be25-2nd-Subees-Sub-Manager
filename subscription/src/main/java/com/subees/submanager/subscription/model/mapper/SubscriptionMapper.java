package com.subees.submanager.subscription.model.mapper;

import com.subees.submanager.subscription.model.dto.CreateSubscriptionRequest;
import com.subees.submanager.subscription.model.vo.Subscription;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SubscriptionMapper {
    List<Subscription> selectAll();

    int countDuplicateSubscription(CreateSubscriptionRequest request);

    void insertSubscription(CreateSubscriptionRequest request);

    Long selectLastInsertedId();
}