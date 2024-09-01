package net.nak.services;

import net.nak.DTO.NotificationDTO;
import net.nak.entities.Notification;
import reactor.core.publisher.Flux;

import java.util.List;

public interface NotificationService {
    void addNotification(String message);
    List<Notification> getAllNotifications();
    long getNotificationCount();

    Flux<NotificationDTO> streamNotifications();

}
