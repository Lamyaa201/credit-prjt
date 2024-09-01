package net.nak.RestControllers;

import javassist.NotFoundException;
import net.nak.DTO.UtilisateurDTO;
import net.nak.entities.Utilisateur;
import net.nak.services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UtilisateurRestController {

    @Autowired
    private UtilisateurService utilisateurService;

    @GetMapping("/userById/{id}")
    public ResponseEntity<Utilisateur> getUtilisateurById(@PathVariable Long id) throws NotFoundException {
        return ResponseEntity.ok(utilisateurService.getUtilisateurById(id));
    }

    @GetMapping("/userByUsername/{username}")
    public ResponseEntity<Utilisateur> getUtilisateurByUsername(@PathVariable String username) throws NotFoundException {
        return ResponseEntity.ok(utilisateurService.findbyUsername(username));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Utilisateur>> getAllUtilisateurs() {
        List<Utilisateur> utilisateurs = utilisateurService.getAllUtilisateurs();
        return ResponseEntity.ok(utilisateurs);
    }

    @PostMapping("/addUser")
    public ResponseEntity<?> addUtilisateur(@RequestBody UtilisateurDTO utilisateurDTO) {
        try {
            Utilisateur addUtilisateur = utilisateurService.addUtilisateur(utilisateurDTO);
            return new ResponseEntity<>(addUtilisateur, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }


    @PutMapping("/updateUser/{id}")
    public ResponseEntity<Utilisateur> updateUtilisateur(
            @PathVariable Long id,
            @RequestBody UtilisateurDTO utilisateurDTO) throws NotFoundException {

        Utilisateur updateUtilisateur = utilisateurService.updateUtilisateur(id, utilisateurDTO);
        if (updateUtilisateur != null) {
            return ResponseEntity.ok(updateUtilisateur);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<String> deleteUtilisateur(@PathVariable Long id) {
        utilisateurService.deleteUtilisateur(id);
        return ResponseEntity.ok("User deleted");
    }

}