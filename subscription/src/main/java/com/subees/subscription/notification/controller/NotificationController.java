package com.subees.subscription.notification.controller;

import com.subees.subscription.notification.model.dto.NotificationCreateRequestDTO;
import com.subees.subscription.notification.model.dto.NotificationResponseDTO;
import com.subees.subscription.notification.model.service.NotificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    // 알림 생성
    @PostMapping
    public ResponseEntity<NotificationResponseDTO> createNotification(
            @RequestBody NotificationCreateRequestDTO requestDTO
    ) {
        NotificationResponseDTO responseDTO = notificationService.createNotification(requestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    // 알림 단건 조회
    @GetMapping
    public ResponseEntity<NotificationResponseDTO> getNotificationById(
            @RequestParam Long notificationId
    ) {
        NotificationResponseDTO responseDTO =
                notificationService.getNotificationById(notificationId);
        return ResponseEntity.ok(responseDTO);
    }

    // 특정 유저 알림 전체 조회
    @GetMapping("/user")
    public ResponseEntity<List<NotificationResponseDTO>> getNotificationsByUserId(
            @RequestParam Long userId
    ) {
        List<NotificationResponseDTO> responseDTOList =
                notificationService.getNotificationsByUserId(userId);
        return ResponseEntity.ok(responseDTOList);
    }

    // 특정 유저 미읽음 알림 조회
    @GetMapping("/user/unread")
    public ResponseEntity<List<NotificationResponseDTO>> getUnreadNotificationsByUserId(
            @RequestParam Long userId
    ) {
        List<NotificationResponseDTO> responseDTOList =
                notificationService.getUnreadNotificationsByUserId(userId);
        return ResponseEntity.ok(responseDTOList);
    }

    // 읽음 처리
    @PatchMapping("/read")
    public ResponseEntity<String> markAsRead(
            @RequestParam Long notificationId
    ) {
        notificationService.markAsRead(notificationId);
        return ResponseEntity.ok("읽음 처리 완료");
    }

    // 닫기 처리
    @PatchMapping("/close")
    public ResponseEntity<String> markAsClosed(
            @RequestParam Long notificationId
    ) {
        notificationService.markAsClosed(notificationId);
        return ResponseEntity.ok("닫기 처리 완료");
    }
}