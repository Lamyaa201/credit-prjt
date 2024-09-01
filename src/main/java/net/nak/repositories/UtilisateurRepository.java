package net.nak.repositories;

import net.nak.DTO.UtilisateurDTO;
import net.nak.entities.Utilisateur;
import net.nak.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface UtilisateurRepository extends JpaRepository<Utilisateur,Long> {

    List<Utilisateur> findByRole(Role role);
    Utilisateur findByUsername(String username);

    Utilisateur findByEmail(String email);
}
