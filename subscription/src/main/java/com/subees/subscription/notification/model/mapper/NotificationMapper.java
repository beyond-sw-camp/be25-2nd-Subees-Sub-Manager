package com.example.notification.mapper;

import com.example.notification.vo.NotificationVO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NotificationMapper {

    @Insert("""
        INSERT INTO notification (
            user_id,
            subscription_id,
            is_read,
            is_closed,
            notify_type,
            created_at,
            alarm
        ) VALUES (
            #{userId},
            #{subscriptionId},
            #{isRead},
            #{isClosed},
            #{notifyType},
            NOW(),
            #{alarm}
        )
    """)
    @Options(useGeneratedKeys = true, keyProperty = "notificationId")
    int insertNotification(NotificationVO notificationVO);

    @Select("""
        SELECT
            notification_id AS notificationId,
            user_id AS userId,
            subscription_id AS subscriptionId,
            is_read AS isRead,
            is_closed AS isClosed,
            notify_type AS notifyType,
            created_at AS createdAt,
            alarm
        FROM notification
        WHERE user_id = #{userId}
        ORDER BY created_at DESC
    """)
    List<NotificationVO> findByUserId(@Param("userId") Long userId);

    @Select("""
        SELECT
            notification_id AS notificationId,
            user_id AS userId,
            subscription_id AS subscriptionId,
            is_read AS isRead,
            is_closed AS isClosed,
            notify_type AS notifyType,
            created_at AS createdAt,
            alarm
        FROM notification
        WHERE notification_id = #{notificationId}
    """)
    NotificationVO findById(@Param("notificationId") Long notificationId);

    @Update("""
        UPDATE notification
        SET is_read = true
        WHERE notification_id = #{notificationId}
    """)
    int markAsRead(@Param("notificationId") Long notificationId);

    @Update("""
        UPDATE notification
        SET is_closed = true
        WHERE notification_id = #{notificationId}
    """)
    int closeNotification(@Param("notificationId") Long notificationId);

    @Delete("""
        DELETE FROM notification
        WHERE notification_id = #{notificationId}
    """)
    int deleteNotification(@Param("notificationId") Long notificationId);
}