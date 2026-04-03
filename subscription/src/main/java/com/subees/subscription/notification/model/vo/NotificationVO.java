package com.example.notification.vo;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class NotificationVO {

    private Long notificationId;
    private Long userId;
    private String content;
    private Boolean isRead;
    private Boolean isClosed;
    private LocalDateTime createdAt;
}