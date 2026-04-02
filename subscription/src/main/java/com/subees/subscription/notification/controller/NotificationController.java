package com.example.notification.controller;

import com.example.notification.dto.NotificationRequestDTO;
import com.example.notification.dto.NotificationResponseDTO;
import com.example.notification.service.NotificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    // 알림 생성
    @PostMapping
    public ResponseEntity<NotificationResponseDTO> createNotification(
            @RequestBody NotificationRequestDTO requestDTO
    ) {
        NotificationResponseDTO responseDTO = notificationService.createNotification(requestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    // 특정 유저의 알림 목록 조회
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<NotificationResponseDTO>> getNotificationsByUserId(
            @PathVariable Long userId
    ) {
        List<NotificationResponseDTO> notificationList =
                notificationService.getNotificationsByUserId(userId);
        return ResponseEntity.ok(notificationList);
    }

    // 단건 조회
    @GetMapping("/{notificationId}")
    public ResponseEntity<NotificationResponseDTO> getNotificationById(
            @PathVariable Long notificationId
    ) {
        NotificationResponseDTO responseDTO =
                notificationService.getNotificationById(notificationId);
        return ResponseEntity.ok(responseDTO);
    }

    // 읽음 처리
    @PatchMapping("/{notificationId}/read")
    public ResponseEntity<String> markAsRead(@PathVariable Long notificationId) {
        notificationService.markAsRead(notificationId);
        return ResponseEntity.ok("알림 읽음 처리 완료");
    }

    // 닫기 처리
    @PatchMapping("/{notificationId}/close")
    public ResponseEntity<String> closeNotification(@PathVariable Long notificationId) {
        notificationService.closeNotification(notificationId);
        return ResponseEntity.ok("알림 닫기 처리 완료");
    }

    // 삭제
    @DeleteMapping("/{notificationId}")
    public ResponseEntity<String> deleteNotification(@PathVariable Long notificationId) {
        notificationService.deleteNotification(notificationId);
        return ResponseEntity.ok("알림 삭제 완료");
    }
}