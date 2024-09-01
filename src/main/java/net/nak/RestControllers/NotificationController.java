package net.nak.RestControllers;

import net.nak.DTO.NotificationDTO;
import net.nak.entities.Notification;
import net.nak.services.NotificationService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping("/send")
    public String sendNotification(@RequestBody Map<String, String> payload) {
        String message = payload.get("message");
        notificationService.addNotification(message);
        return "Notification sent: " + message;
    }

    @GetMapping
    public List<Notification> getNotifications() {
        return notificationService.getAllNotifications();
    }

    @GetMapping("/stream")
    public Flux<NotificationDTO> streamNotifications() {
        return notificationService.streamNotifications();
    }

    @GetMapping("/count")
    public long getNotificationCount() {
        return notificationService.getNotificationCount();
    }

}
