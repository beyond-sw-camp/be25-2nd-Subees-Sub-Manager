package com.subees.subscription.notification.model.service;

import com.example.notification.dto.NotificationCreateRequestDTO;
import com.example.notification.dto.NotificationResponseDTO;

import java.util.List;

public interface NotificationService {

    NotificationResponseDTO createNotification(NotificationCreateRequestDTO requestDTO);

    NotificationResponseDTO getNotificationById(Long notificationId);

    List<NotificationResponseDTO> getNotificationsByUserId(Long userId);

    List<NotificationResponseDTO> getUnreadNotificationsByUserId(Long userId);

    void markAsRead(Long notificationId);

    void markAsClosed(Long notificationId);
}