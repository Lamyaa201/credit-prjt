package net.nak;

import net.nak.DTO.UtilisateurDTO;
import net.nak.entities.Utilisateur;
import net.nak.enums.Role;
import net.nak.services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import java.time.LocalDate;

@SpringBootApplication
@EnableScheduling
 public class BackendApplication implements CommandLineRunner {

        @Autowired
        private UtilisateurService utilisateurService;
        public static void main(String[] args) {  SpringApplication.run(BackendApplication.class, args);}

      @Override
        public void run(String... args) throws Exception {
  /* UtilisateurDTO utilisateurDTO = new UtilisateurDTO();
            utilisateurDTO.setUsername("zineb");
            utilisateurDTO.setPrenom("ben");
            utilisateurDTO.setNaissance(LocalDate.parse("2000-01-01"));
            utilisateurDTO.setEmail("zineb@gmail.com");
            utilisateurDTO.setNumerotel("060054321");
            utilisateurDTO.setAdresse("casablanca, 2 mars ");
            utilisateurDTO.setRole(Role.UTILISATEUR);
            utilisateurDTO.setPassword("123");

            Utilisateur utilisateur = utilisateurService.addUtilisateur(utilisateurDTO);
            utilisateurService.createUserInKeycloak(utilisateurDTO.getUsername(), utilisateurDTO.getPassword());

            System.out.println("Utilisateur ajouté avec succès !"); */
        }
    }