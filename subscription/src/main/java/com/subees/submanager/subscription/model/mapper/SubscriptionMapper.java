package com.subees.submanager.subscription.model.mapper;

import com.subees.submanager.subscription.model.dto.CreateSubscriptionRequestdto;
import com.subees.submanager.subscription.model.vo.Subscription;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SubscriptionMapper {
    List<Subscription> selectAll();

    int countDuplicateSubscription(CreateSubscriptionRequestdto request);

    int insertSubscription(CreateSubscriptionRequestdto request);

    Long selectLastInsertedId();
}