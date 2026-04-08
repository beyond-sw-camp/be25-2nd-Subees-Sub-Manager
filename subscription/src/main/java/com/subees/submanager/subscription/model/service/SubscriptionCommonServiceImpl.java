package com.subees.submanager.subscription.model.service;

import com.subees.submanager.subscription.model.dto.common.BillingCycleResponse;
import com.subees.submanager.subscription.model.dto.common.CategoryResponse;
import com.subees.submanager.subscription.model.dto.common.ItemResponse;
import com.subees.submanager.subscription.model.mapper.SubscriptionCommonMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

;

@Service
@RequiredArgsConstructor
public class SubscriptionCommonServiceImpl implements SubscriptionCommonService {

    private final SubscriptionCommonMapper subscriptionCommonMapper;

    @Override
    public List<CategoryResponse> getCategories() {
        return subscriptionCommonMapper.selectAllCategories();
    }

    @Override
    public List<ItemResponse> getItemsByCategoryId(Long categoryId) {
        if (categoryId == null) {
            throw new IllegalArgumentException("카테고리 ID는 필수입니다.");
        }
        return subscriptionCommonMapper.selectItemsByCategoryId(categoryId);
    }

    @Override
    public List<BillingCycleResponse> getBillingCycles() {
        return List.of(
                new BillingCycleResponse("1M", "1개월"),
                new BillingCycleResponse("1Y", "1년")
        );
    }
}