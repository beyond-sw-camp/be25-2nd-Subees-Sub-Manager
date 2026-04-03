package com.example.notification.mapper;

import com.example.notification.vo.NotificationVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface NotificationMapper {

    int insertNotification(NotificationVO notificationVO);

    List<NotificationVO> selectNotificationsByUserId(@Param("userId") Long userId);

    int updateNotificationAsRead(@Param("notificationId") Long notificationId);

    int updateNotificationAsClosed(@Param("notificationId") Long notificationId);
}