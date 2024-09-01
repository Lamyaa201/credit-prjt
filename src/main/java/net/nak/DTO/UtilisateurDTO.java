package net.nak.DTO;

import lombok.*;
import net.nak.enums.Role;

import java.time.LocalDate;

@Data
public class UtilisateurDTO {
    private Long id;

    private String username;
    private String prenom;
    private LocalDate naissance;
    private String email;
    private String adresse;
    private String numerotel;
    private String password;
    private Role role;

    private String photoUrl;
}
