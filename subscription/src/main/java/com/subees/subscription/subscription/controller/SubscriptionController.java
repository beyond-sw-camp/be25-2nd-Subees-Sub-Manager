package com.subees.subscription.subscription.controller;

import com.subees.subscription.subscription.model.dto.CreateSubscriptionRequest;
import com.subees.subscription.subscription.model.dto.UpdateSubscriptionRequest;
import com.subees.subscription.subscription.model.dto.UpdateSubscriptionStatusRequest;
import com.subees.subscription.subscription.model.vo.Subscription;
import com.subees.subscription.subscription.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/subscriptions")
@RequiredArgsConstructor
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createSubscription(@RequestBody CreateSubscriptionRequest request) {
        subscriptionService.createSubscription(request);
    }

    @GetMapping
    public List<Subscription> getSubscriptions() {
        return subscriptionService.getSubscriptions();
    }

    @GetMapping("/{id}")
    public Subscription getSubscription(@PathVariable("id") Long subscriptionId) {
        return subscriptionService.getSubscription(subscriptionId);
    }

    @PutMapping("/{id}")
    public void updateSubscription(@PathVariable("id") Long subscriptionId,
                                   @RequestBody UpdateSubscriptionRequest request) {
        subscriptionService.updateSubscription(subscriptionId, request);
    }

    @DeleteMapping("/{id}")
    public void deleteSubscription(@PathVariable("id") Long subscriptionId) {
        subscriptionService.deleteSubscription(subscriptionId);
    }

    @PatchMapping("/{id}/status")
    public void updateSubscriptionStatus(@PathVariable("id") Long subscriptionId,
                                         @RequestBody UpdateSubscriptionStatusRequest request) {
        subscriptionService.updateSubscriptionStatus(subscriptionId, request);
    }
}