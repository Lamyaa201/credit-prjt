package net.nak.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationDTO {
    private Long id;
    private String message;
    private LocalDateTime timestamp;
    private boolean read;

    // Ce constructeur pourrait être utilisé dans des cas où l'ID est généré automatiquement et l'état de lecture est par défaut faux.
    public NotificationDTO(String message, LocalDateTime timestamp) {
        this.message = message;
        this.timestamp = timestamp;
        this.read = false;
    }
}
