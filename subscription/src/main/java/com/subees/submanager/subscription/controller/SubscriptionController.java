package com.subees.submanager.subscription.controller;

import com.subees.submanager.common.model.dto.BaseResponseDto;
import com.subees.submanager.common.model.dto.ItemsResponseDto;
import com.subees.submanager.subscription.model.dto.CreateSubscriptionRequest;
import com.subees.submanager.subscription.model.dto.CreateSubscriptionResponse;
import com.subees.submanager.subscription.model.vo.Subscription;
import com.subees.submanager.subscription.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/v1/subscriptions")
@RequiredArgsConstructor
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    @GetMapping
    public ResponseEntity<ItemsResponseDto<Subscription>> getSubscriptions() {
        List<Subscription> subscriptions = subscriptionService.getSubscriptions();

        return ResponseEntity.ok(
                new ItemsResponseDto<>(HttpStatus.OK, subscriptions, 1, subscriptions.size())
        );
    }

    @PostMapping
    public ResponseEntity<BaseResponseDto<CreateSubscriptionResponse>> createSubscription(
            @RequestBody CreateSubscriptionRequest request
    ) {
        CreateSubscriptionResponse response = subscriptionService.createSubscription(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new BaseResponseDto<>(HttpStatus.CREATED, response));
    }
}
