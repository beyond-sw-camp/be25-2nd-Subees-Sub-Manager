package com.subees.subscription.notification.model.mapper;

import com.subees.subscription.notification.model.vo.NotificationVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface NotificationMapper {

    int insertNotification(NotificationVO notificationVO);

    NotificationVO selectNotificationById(@Param("notificationId") Long notificationId);

    List<NotificationVO> selectNotificationsByUserId(@Param("userId") Long userId);

    List<NotificationVO> selectUnreadNotificationsByUserId(@Param("userId") Long userId);

    int updateReadStatus(@Param("notificationId") Long notificationId);

    int updateClosedStatus(@Param("notificationId") Long notificationId);
}