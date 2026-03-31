import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "notification")
@Getter
@NoArgsConstructor
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notification_id")
    private Long notificationId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "subscription_id")
    private Long subscriptionId;

    @Column(name = "is_read", nullable = false)
    private boolean isRead = false;

    @Column(name = "is_closed", nullable = false)
    private boolean isClosed = false;

    @Enumerated(EnumType.STRING)
    @Column(name = "notify_type", nullable = false)
    private AlarmType alarmType;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "alarm")
    private String alarm;

    // 생성자
    public Alarm(Long userId, Long subscriptionId, String alarm, NotifyType notifyType) {
        this.userId = userId;
        this.subscriptionId = subscriptionId;
        this.alarm = alarm;
        this.notifyType = notifyType;
        this.createdAt = LocalDateTime.now();
        this.isRead = false;
        this.isClosed = false;
    }

    // 🔹 비즈니스 메서드
    public void markAsRead() {
        this.isRead = true;
    }

    public void close() {
        this.isClosed = true;
    }
}