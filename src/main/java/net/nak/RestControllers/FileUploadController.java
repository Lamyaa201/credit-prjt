package net.nak.RestControllers;

import net.nak.entities.Utilisateur;
import net.nak.services.UtilisateurService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Controller
@RequestMapping("/api/users")
public class FileUploadController {

    @Value("${file.upload-dir}")
    private String uploadDir;

    private final UtilisateurService utilisateurService;

    public FileUploadController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @PostMapping("/{id}/upload-photo")
    public ResponseEntity<String> uploadPhoto(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
        try {
            // Vérifier si le fichier est vide
            if (file.isEmpty()) {
                return new ResponseEntity<>("No file selected", HttpStatus.BAD_REQUEST);
            }
            // Générer un nom de fichier unique
            String fileName = UUID.randomUUID() + "-" + file.getOriginalFilename();
            Path filePath = Paths.get(uploadDir, fileName);
            // Sauvegarder le fichier
            Files.createDirectories(filePath.getParent());
            Files.write(filePath, file.getBytes());
            // Mettre à jour l'URL de la photo de l'utilisateur
            Utilisateur utilisateur = utilisateurService.findById(id);
            utilisateur.setPhotoUrl(fileName);
            utilisateurService.save(utilisateur);
            // Retourner le nom du fichier en réponse
            return new ResponseEntity<>(fileName, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Failed to upload file", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/photos/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
        try {
            Path file = Paths.get(uploadDir).resolve(filename);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                        .body(resource);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
