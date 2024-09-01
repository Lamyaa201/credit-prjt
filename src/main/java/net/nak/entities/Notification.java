package net.nak.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String message;
    private LocalDateTime timestamp;
    private boolean read; // Nouvel attribut pour l'état de lecture

    // Constructeur utilisé pour les nouvelles notifications
    public Notification(String message, LocalDateTime timestamp) {
        this.message = message;
        this.timestamp = timestamp;
        this.read = false; // Par défaut, la notification n'est pas lue
    }

}
