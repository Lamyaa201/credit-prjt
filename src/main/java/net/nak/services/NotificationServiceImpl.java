package net.nak.services;

import net.nak.DTO.NotificationDTO;
import net.nak.entities.Notification;
import net.nak.repositories.NotificationRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final Sinks.Many<NotificationDTO> sink = Sinks.many().multicast().onBackpressureBuffer();

    public NotificationServiceImpl(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Override
    public void addNotification(String message) {
        LocalDateTime now = LocalDateTime.now();
        Notification notification = new Notification(message, now);
        notificationRepository.save(notification);

        // Crée un DTO à partir de l'entité Notification sauvegardée
        NotificationDTO notificationDTO = new NotificationDTO(notification.getId(), notification.getMessage(), notification.getTimestamp(), notification.isRead());
        sink.tryEmitNext(notificationDTO); // Émet la notificationDTO à tous les abonnés
    }
    @Override
    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }

    @Override
    public long getNotificationCount() {
        return notificationRepository.findByReadFalse().size(); // Retourne le nombre de notifications non lues
    }

    @Override
    public Flux<NotificationDTO> streamNotifications() {
        return sink.asFlux();
    }

}
