package com.subees.submanager.notification.controller;

import com.subees.submanager.notification.model.dto.NotificationResponse;
import com.subees.submanager.notification.model.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    @GetMapping
    public ResponseEntity<Map<String, Object>> getNotifications() {
        Long userId = 1L;   // 테스트용, 나중에 로그인 유저로 교체

        List<NotificationResponse> notifications = notificationService.getNotifications(userId);

        Map<String, Object> response = new HashMap<>();
        response.put("status", 200);
        response.put("message", "알림 목록 조회 성공");
        response.put("data", notifications);

        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{notificationId}/read")
    public ResponseEntity<Map<String, Object>> readNotification(@PathVariable Long notificationId) {
        notificationService.readNotification(notificationId);

        Map<String, Object> response = new HashMap<>();
        response.put("status", 200);
        response.put("message", "알림 읽음 처리 성공");

        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{notificationId}/close")
    public ResponseEntity<Map<String, Object>> closeNotification(@PathVariable Long notificationId) {
        notificationService.closeNotification(notificationId);

        Map<String, Object> response = new HashMap<>();
        response.put("status", 200);
        response.put("message", "알림 닫기 처리 성공");

        return ResponseEntity.ok(response);
    }
}