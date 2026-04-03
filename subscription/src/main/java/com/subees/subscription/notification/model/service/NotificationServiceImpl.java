package com.example.notification.service;

import com.example.notification.dto.NotificationRequestDTO;
import com.example.notification.dto.NotificationResponseDTO;
import com.example.notification.mapper.NotificationMapper;
import com.example.notification.vo.NotificationVO;
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
    public NotificationResponseDTO createNotification(NotificationRequestDTO requestDTO) {
        NotificationVO vo = new NotificationVO();
        vo.setUserId(requestDTO.getUserId());
        vo.setContent(requestDTO.getContent());
        vo.setIsRead(false);
        vo.setIsClosed(false);

        notificationMapper.insertNotification(vo);

        return toResponseDTO(vo);
    }

    @Override
    @Transactional(readOnly = true)
    public List<NotificationResponseDTO> getNotificationsByUserId(Long userId) {
        List<NotificationVO> notificationList = notificationMapper.selectNotificationsByUserId(userId);

        return notificationList.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void markAsRead(Long notificationId) {
        int result = notificationMapper.updateNotificationAsRead(notificationId);
        if (result == 0) {
            throw new RuntimeException("해당 알림이 존재하지 않습니다. notificationId=" + notificationId);
        }
    }

    @Override
    public void closeNotification(Long notificationId) {
        int result = notificationMapper.updateNotificationAsClosed(notificationId);
        if (result == 0) {
            throw new RuntimeException("해당 알림이 존재하지 않습니다. notificationId=" + notificationId);
        }
    }

    private NotificationResponseDTO toResponseDTO(NotificationVO vo) {
        NotificationResponseDTO dto = new NotificationResponseDTO();
        dto.setNotificationId(vo.getNotificationId());
        dto.setUserId(vo.getUserId());
        dto.setContent(vo.getContent());
        dto.setIsRead(vo.getIsRead());
        dto.setIsClosed(vo.getIsClosed());
        dto.setCreatedAt(vo.getCreatedAt());
        return dto;
    }
}