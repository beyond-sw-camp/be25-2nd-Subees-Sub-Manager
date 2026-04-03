package com.example.notification.service;

import com.example.notification.dto.NotificationRequestDTO;
import com.example.notification.dto.NotificationResponseDTO;
import com.example.notification.mapper.NotificationMapper;
import com.example.notification.vo.NotificationVO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificationServiceImpl implements NotificationService {

    private final NotificationMapper notificationMapper;

    public NotificationServiceImpl(NotificationMapper notificationMapper) {
        this.notificationMapper = notificationMapper;
    }

    @Override
    public NotificationResponseDTO createNotification(NotificationRequestDTO requestDTO) {
        NotificationVO vo = new NotificationVO();
        vo.setUserId(requestDTO.getUserId());
        vo.setSubscriptionId(requestDTO.getSubscriptionId());
        vo.setNotifyType(requestDTO.getNotifyType());
        vo.setAlarm(requestDTO.getAlarm());
        vo.setIsRead(false);
        vo.setIsClosed(false);

        notificationMapper.insertNotification(vo);

        NotificationVO savedVO = notificationMapper.findById(vo.getNotificationId());
        return toResponseDTO(savedVO);
    }

    @Override
    public List<NotificationResponseDTO> getNotificationsByUserId(Long userId) {
        List<NotificationVO> notificationList = notificationMapper.findByUserId(userId);
        return notificationList.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public NotificationResponseDTO getNotificationById(Long notificationId) {
        NotificationVO vo = notificationMapper.findById(notificationId);
        if (vo == null) {
            throw new RuntimeException("알림이 존재하지 않습니다. id=" + notificationId);
        }
        return toResponseDTO(vo);
    }

    @Override
    public void markAsRead(Long notificationId) {
        int result = notificationMapper.markAsRead(notificationId);
        if (result == 0) {
            throw new RuntimeException("읽음 처리할 알림이 존재하지 않습니다. id=" + notificationId);
        }
    }

    @Override
    public void closeNotification(Long notificationId) {
        int result = notificationMapper.closeNotification(notificationId);
        if (result == 0) {
            throw new RuntimeException("닫기 처리할 알림이 존재하지 않습니다. id=" + notificationId);
        }
    }

    @Override
    public void deleteNotification(Long notificationId) {
        int result = notificationMapper.deleteNotification(notificationId);
        if (result == 0) {
            throw new RuntimeException("삭제할 알림이 존재하지 않습니다. id=" + notificationId);
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