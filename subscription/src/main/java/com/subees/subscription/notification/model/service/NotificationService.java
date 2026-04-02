package com.example.notification.service;

import com.example.notification.dto.NotificationRequestDTO;
import com.example.notification.dto.NotificationResponseDTO;

import java.util.List;

public interface NotificationService {

    NotificationResponseDTO createNotification(NotificationRequestDTO requestDTO);

    List<NotificationResponseDTO> getNotificationsByUserId(Long userId);

    NotificationResponseDTO getNotificationById(Long notificationId);

    void markAsRead(Long notificationId);

    void closeNotification(Long notificationId);

    void deleteNotification(Long notificationId);
}