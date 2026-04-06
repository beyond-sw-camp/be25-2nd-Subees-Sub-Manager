package com.subees.subscription.notification.model.service;

import com.subees.subscription.notification.model.dto.NotificationCreateRequestDTO;
import com.subees.subscription.notification.model.dto.NotificationResponseDTO;
import com.subees.subscription.notification.model.mapper.NotificationMapper;
import com.subees.subscription.notification.model.vo.NotificationVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class NotificationServiceImpl implements NotificationService {

    private final NotificationMapper notificationMapper;

    public NotificationServiceImpl(NotificationMapper notificationMapper) {
        this.notificationMapper = notificationMapper;
    }

    @Override
    public NotificationResponseDTO createNotification(NotificationCreateRequestDTO requestDTO) {
        NotificationVO vo = new NotificationVO();
        vo.setUserId(requestDTO.getUserId());
        vo.setSubscriptionId(requestDTO.getSubscriptionId());
        vo.setNotifyType(requestDTO.getNotifyType());
        vo.setAlarm(requestDTO.getAlarm());
        vo.setIsRead(false);
        vo.setIsClosed(false);

        notificationMapper.insertNotification(vo);

        NotificationVO saved = notificationMapper.selectNotificationById(vo.getNotificationId());
        return toResponseDTO(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public NotificationResponseDTO getNotificationById(Long notificationId) {
        NotificationVO vo = notificationMapper.selectNotificationById(notificationId);
        if (vo == null) {
            throw new IllegalArgumentException("해당 알림이 존재하지 않습니다. notificationId=" + notificationId);
        }
        return toResponseDTO(vo);
    }

    @Override
    @Transactional(readOnly = true)
    public List<NotificationResponseDTO> getNotificationsByUserId(Long userId) {
        return notificationMapper.selectNotificationsByUserId(userId)
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<NotificationResponseDTO> getUnreadNotificationsByUserId(Long userId) {
        return notificationMapper.selectUnreadNotificationsByUserId(userId)
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void markAsRead(Long notificationId) {
        int updated = notificationMapper.updateReadStatus(notificationId);
        if (updated == 0) {
            throw new IllegalArgumentException("읽음 처리할 알림이 존재하지 않습니다. notificationId=" + notificationId);
        }
    }

    @Override
    public void markAsClosed(Long notificationId) {
        int updated = notificationMapper.updateClosedStatus(notificationId);
        if (updated == 0) {
            throw new IllegalArgumentException("닫기 처리할 알림이 존재하지 않습니다. notificationId=" + notificationId);
        }
    }

    private NotificationResponseDTO toResponseDTO(NotificationVO vo) {
        NotificationResponseDTO dto = new NotificationResponseDTO();
        dto.setNotificationId(vo.getNotificationId());
        dto.setUserId(vo.getUserId());
        dto.setSubscriptionId(vo.getSubscriptionId());
        dto.setIsRead(vo.getIsRead());
        dto.setIsClosed(vo.getIsClosed());
        dto.setNotifyType(vo.getNotifyType());
        dto.setCreatedAt(vo.getCreatedAt());
        dto.setAlarm(vo.getAlarm());
        return dto;
    }
}