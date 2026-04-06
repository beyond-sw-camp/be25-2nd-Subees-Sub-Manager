package com.subees.submanager.subscription.controller;

import com.subees.submanager.subscription.model.dto.common.BillingCycleResponse;
import com.subees.submanager.subscription.model.dto.common.CategoryResponse;
import com.subees.submanager.subscription.model.dto.common.ItemResponse;
import com.subees.submanager.subscription.service.SubscriptionCommonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

;
;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/subscriptions")
public class SubscriptionCommonController {

    private final SubscriptionCommonService subscriptionCommonService;

    @GetMapping("/categories")
    public ResponseEntity<Map<String, Object>> getCategories() {
        List<CategoryResponse> categories = subscriptionCommonService.getCategories();

        Map<String, Object> response = new HashMap<>();
        response.put("status", 200);
        response.put("message", "카테고리 목록 조회 성공");
        response.put("data", categories);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/category-items/{categoryId}")
    public ResponseEntity<Map<String, Object>> getItemsByCategoryId(@PathVariable Long categoryId) {
        List<ItemResponse> items = subscriptionCommonService.getItemsByCategoryId(categoryId);

        Map<String, Object> response = new HashMap<>();
        response.put("status", 200);
        response.put("message", "카테고리별 구독항목 조회 성공");
        response.put("data", items);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/billing-cycles")
    public ResponseEntity<Map<String, Object>> getBillingCycles() {
        List<BillingCycleResponse> billingCycles = subscriptionCommonService.getBillingCycles();

        Map<String, Object> response = new HashMap<>();
        response.put("status", 200);
        response.put("message", "결제 주기 목록 조회 성공");
        response.put("data", billingCycles);

        return ResponseEntity.ok(response);
    }
}