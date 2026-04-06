package com.subees.submanager.subscription.model.mapper;

import com.subees.submanager.subscription.model.dto.crud.CreateSubscriptionRequest;
import com.subees.submanager.subscription.model.dto.crud.SubscriptionListResponse;
import com.subees.submanager.subscription.model.dto.crud.SubscriptionResponse;
import com.subees.submanager.subscription.model.dto.crud.UpdateSubscriptionRequest;
import com.subees.submanager.subscription.model.vo.Subscription;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SubscriptionMapper {
    List<SubscriptionListResponse> selectAll();

    int countDuplicateSubscription(CreateSubscriptionRequest request);

    void insertSubscription(CreateSubscriptionRequest request);

    Long selectLastInsertedId();

    SubscriptionResponse selectById(Long subscriptionId);

    Subscription selectSubscriptionById(@Param("subscriptionId") Long subscriptionId);

    int updateSubscription(@Param("subscriptionId") Long subscriptionId,
                           @Param("request") UpdateSubscriptionRequest request);

    int existsPaymentMethod(Long paymentId);

    int softDeleteSubscription(Long subscriptionId);
}
