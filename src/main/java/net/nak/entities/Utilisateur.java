package net.nak.entities;

import javax.persistence.*;
import lombok.*;
import net.nak.enums.Role;

import java.time.LocalDate;

@Entity
@AllArgsConstructor @NoArgsConstructor @Getter @Setter
@Table(name="Utilisateur")
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "utilisateur_id")
    private Long id;

    @Column(name = "username", updatable = false, nullable = false)
    private String username;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "email")
    private String email;

    @Column(name = "naissance")
    private LocalDate naissance;

    @Column(name = "adresse")
    private String adresse;

    @Column(name = "numerotel")
    private String numerotel;

    @Column(name = "password")
    private String password;

    @Column(name = "photo_url")
    private String photoUrl;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

}