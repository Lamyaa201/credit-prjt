package net.nak.services;

import javassist.NotFoundException;
import net.nak.DTO.UtilisateurDTO;
import net.nak.entities.Utilisateur;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import java.util.List;

public interface UtilisateurService {
    Utilisateur getUtilisateurById(Long id) throws NotFoundException;
    List<Utilisateur> getAllUtilisateurs();
    Utilisateur addUtilisateur(UtilisateurDTO utilisateurDTO);
    Utilisateur updateUtilisateur(Long id, UtilisateurDTO utilisateurDTO) throws NotFoundException;
    void deleteUtilisateur(Long id);
    Utilisateur findByEmail(String email);
    Utilisateur findbyUsername(String username) throws NotFoundException;
    String getKeycloakAdminToken();
    void createUserInKeycloak(String username, String password);
    Utilisateur save(@NonNull Utilisateur utilisateur);
    Utilisateur findById(Long id);

}