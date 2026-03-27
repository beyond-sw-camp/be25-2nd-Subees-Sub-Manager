package com.subees.subscription.subscription.mapper;

import com.subees.subscription.subscription.model.vo.Subscription;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SubscriptionMapper {

    int insertSubscription(Subscription subscription);

    List<Subscription> findAllSubscriptions();

    Subscription findSubscriptionById(@Param("subscriptionId") Long subscriptionId);

    int updateSubscription(Subscription subscription);

    int deleteSubscription(@Param("subscriptionId") Long subscriptionId);

    int updateSubscriptionStatus(@Param("subscriptionId") Long subscriptionId,
                                 @Param("useYn") String useYn);
}