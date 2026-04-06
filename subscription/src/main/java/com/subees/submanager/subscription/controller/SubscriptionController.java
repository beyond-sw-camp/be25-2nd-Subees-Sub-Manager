package com.subees.submanager.subscription.controller;

import com.subees.submanager.common.model.dto.BaseResponseDto;
import com.subees.submanager.subscription.model.dto.crud.CreateSubscriptionRequest;
import com.subees.submanager.subscription.model.dto.crud.CreateSubscriptionResponse;
import com.subees.submanager.subscription.model.dto.crud.SubscriptionListResponse;
import com.subees.submanager.subscription.model.dto.crud.SubscriptionResponse;
import com.subees.submanager.subscription.model.dto.crud.UpdateSubscriptionRequest;
import com.subees.submanager.subscription.model.dto.crud.UpdateSubscriptionResponse;
import com.subees.submanager.subscription.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/v1/subscriptions")
@RequiredArgsConstructor
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    // 조회 api
    @GetMapping
    public ResponseEntity<List<SubscriptionListResponse>> getSubscriptions() {
        return ResponseEntity.ok(subscriptionService.getSubscriptions());
    }

    // 상세조회 api
    @GetMapping("/{subscriptionId}")
    public ResponseEntity<SubscriptionResponse> getSubscriptionById(@PathVariable Long subscriptionId) {
        return ResponseEntity.ok(subscriptionService.getSubscriptionById(subscriptionId));
    }


    // 등록 api
    @PostMapping
    public ResponseEntity<BaseResponseDto<CreateSubscriptionResponse>> createSubscription(
            @RequestBody CreateSubscriptionRequest request
    ) {
        CreateSubscriptionResponse response = subscriptionService.createSubscription(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new BaseResponseDto<>(HttpStatus.CREATED, response));
    }

    // 수정 api
    @PutMapping("/{id}")
    public ResponseEntity<?> updateSubscription(@PathVariable("id") Long subscriptionId,
                                                @RequestBody UpdateSubscriptionRequest request) {

        UpdateSubscriptionResponse response =
                subscriptionService.updateSubscription(subscriptionId, request);

        return ResponseEntity.ok(Map.of(
                "status", 200,
                "message", "수정이 완료되었습니다.",
                "data", response
        ));
    }
}
