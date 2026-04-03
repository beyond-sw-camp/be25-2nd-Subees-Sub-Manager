@RestController
@RequestMapping("/api/v1/notification")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    // 알림 생성 (body 그대로 유지)
    @PostMapping("/create")
    public ResponseEntity<NotificationResponseDTO> createNotification(
            @RequestBody NotificationRequestDTO requestDTO
    ) {
        NotificationResponseDTO responseDTO = notificationService.createNotification(requestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    // 특정 유저의 알림 목록 조회
    @GetMapping("/user")
    public ResponseEntity<List<NotificationResponseDTO>> getNotificationsByUserId(
            @RequestParam Long userId
    ) {
        List<NotificationResponseDTO> notificationList =
                notificationService.getNotificationsByUserId(userId);
        return ResponseEntity.ok(notificationList);
    }

    // 읽음 처리
    @PatchMapping("/read")
    public ResponseEntity<String> markAsRead(
            @RequestParam Long notificationId
    ) {
        notificationService.markAsRead(notificationId);
        return ResponseEntity.ok("알림 읽음 처리 완료");
    }

    // 닫기 처리
    @PatchMapping("/close")
    public ResponseEntity<String> closeNotification(
            @RequestParam Long notificationId
    ) {
        notificationService.closeNotification(notificationId);
        return ResponseEntity.ok("알림 닫기 처리 완료");
    }
}