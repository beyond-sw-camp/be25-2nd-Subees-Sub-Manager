package com.subees.submanager.subscription.service;

import com.subees.submanager.subscription.model.dto.crud.CreateSubscriptionRequest;
import com.subees.submanager.subscription.model.dto.crud.CreateSubscriptionResponse;
import com.subees.submanager.subscription.model.dto.crud.SubscriptionListResponse;
import com.subees.submanager.subscription.model.dto.crud.SubscriptionResponse;
import com.subees.submanager.subscription.model.dto.crud.UpdateSubscriptionRequest;
import com.subees.submanager.subscription.model.dto.crud.UpdateSubscriptionResponse;
import com.subees.submanager.subscription.model.mapper.SubscriptionMapper;
import com.subees.submanager.subscription.model.vo.Subscription;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {

    private final SubscriptionMapper subscriptionMapper;

    @Override
    public List<SubscriptionListResponse> getSubscriptions() {
        return subscriptionMapper.selectAll();
    }

    @Override
    public CreateSubscriptionResponse createSubscription(CreateSubscriptionRequest request) {

        if (request.getPrice() == null || request.getPrice() < 0) {
            throw new IllegalArgumentException("결제금액이 0원 이상이어야 합니다.");
        }

        if (request.getItemId() == null) {
            throw new IllegalArgumentException("항목을 선택하거나 직접 입력해 주세요.");
        }

        if (request.getStartDate() == null) {
            throw new IllegalArgumentException("결제 시작일은 필수입니다.");
        }

        if (request.getBillingCycle() == null) {
            throw new IllegalArgumentException("결제 주기를 선택해주세요.");
        }
        if (request.getCategoryId() == null) {
            throw new IllegalArgumentException("카테고리를 선택해 주세요.");
        }

        if (request.getPaymentId() == null) {
            throw new IllegalArgumentException("결제수단을 선택해 주세요.");
        }

        if (request.getBillingCycle().isBlank()) {
            throw new IllegalArgumentException("결제 주기를 선택해주세요.");
        }

        int duplicateCount = subscriptionMapper.countDuplicateSubscription(request);
        if (duplicateCount > 0) {
            throw new IllegalStateException("이미 등록된 구독항목 입니다.");
        }

        subscriptionMapper.insertSubscription(request);
        Long subscriptionId = subscriptionMapper.selectLastInsertedId();

        return CreateSubscriptionResponse.builder()
                .subscriptionId(subscriptionId)
                .message("구독 항목이 성공적으로 등록되었습니다.")
                .createdAt(LocalDate.now())
                .build();
    }

    @Override
    public SubscriptionResponse getSubscriptionById(Long subscriptionId) {
        SubscriptionResponse response = subscriptionMapper.selectById(subscriptionId);

        if (response == null) {
            throw new NoSuchElementException("해당 구독 항목이 존재하지 않습니다.");
        }

        return response;
    }


    @Override
    public UpdateSubscriptionResponse updateSubscription(Long subscriptionId, UpdateSubscriptionRequest request) {

        Subscription existing = subscriptionMapper.selectSubscriptionById(subscriptionId);
        if (existing == null) {
            throw new IllegalArgumentException("해당 구독이 존재하지 않습니다.");
        }

        if (request.getCategoryId() == null) {
            throw new IllegalArgumentException("카테고리를 선택해 주세요.");
        }

        if (request.getItemId() == null) {
            throw new IllegalArgumentException("항목을 선택하거나 직접 입력해 주세요.");
        }

        if (request.getPrice() == null || request.getPrice() < 0) {
            throw new IllegalArgumentException("결제 금액은 0원 이상이어야 합니다.");
        }

        if (request.getBillingCycle() == null || request.getBillingCycle().isBlank()) {
            throw new IllegalArgumentException("결제 주기를 선택해 주세요.");
        }

        if (request.getStartDate() == null) {
            throw new IllegalArgumentException("다음 결제일은 필수입니다.");
        }

        if (request.getPaymentId() == null) {
            throw new IllegalArgumentException("결제 수단을 선택해 주세요.");
        }

        int result = subscriptionMapper.updateSubscription(subscriptionId, request);

        if (result == 0) {
            throw new IllegalStateException("구독 수정에 실패했습니다.");
        }
        if (subscriptionMapper.existsPaymentMethod(request.getPaymentId()) == 0) {
            throw new IllegalArgumentException("존재하지 않는 결제수단입니다.");
        }

        return UpdateSubscriptionResponse.builder()
                .subscriptionId(subscriptionId)
                .updatedAt(LocalDate.now())
                .build();
    }

    @Override
    public void deleteSubscription(Long subscriptionId) {

        int result = subscriptionMapper.softDeleteSubscription(subscriptionId);

        if (result == 0) {
            throw new IllegalArgumentException("해당 구독이 존재하지 않거나 이미 삭제되었습니다.");
        }
    }
}