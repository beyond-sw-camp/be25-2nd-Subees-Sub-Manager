package com.subees.submanager.notification.model.service;

import com.subees.submanager.notification.model.dto.NotificationResponse;
import com.subees.submanager.notification.model.mapper.NotificationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements com.subees.submanager.notification.model.service.NotificationService {

    private final NotificationMapper notificationMapper;

    @Override
    public List<NotificationResponse> getNotifications(Long userId) {
        notificationMapper.insertNotifications(userId);
        return notificationMapper.selectNotificationsByUserId(userId);
    }

    @Override
    public void readNotification(Long notificationId) {
        int exists = notificationMapper.existsReadableNotification(notificationId);
        System.out.println("exists = " + exists);

        notificationMapper.updateNotificationRead(notificationId);
        System.out.println("read update done");
    }

    @Override
    public void closeNotification(Long notificationId) {
        int exists = notificationMapper.existsClosableNotification(notificationId);
        System.out.println("close exists = " + exists);

        notificationMapper.updateNotificationClose(notificationId);
        System.out.println("close update done");
    }

    @Override
    public void generateNotifications(Long userId) {
        notificationMapper.insertNotifications(userId);
    }
}