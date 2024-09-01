package net.nak.services;

import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import net.nak.DTO.UtilisateurDTO;
import net.nak.entities.Utilisateur;
import net.nak.enums.Role;
import net.nak.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.json.JSONArray;
import org.springframework.lang.NonNull;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.http.*;
import org.json.JSONObject;

import javax.transaction.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class UtilisateurServiceImpl implements UtilisateurService{

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    private final PasswordEncoder passwordEncoder; // Injecter PasswordEncoder

    public Utilisateur getUtilisateurById(Long id) throws NotFoundException {
        Utilisateur utilisateur = utilisateurRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));
        return utilisateur;
    }

    public Utilisateur findbyUsername(String username) throws NotFoundException {
        Utilisateur utilisateur = utilisateurRepository.findByUsername(username);
        if (utilisateur == null) {
            throw new NotFoundException("User not found with username: " + username);
        }
        return utilisateur;
    }

    public List<Utilisateur> getAllUtilisateurs() {
        List<Utilisateur> utilisateurs = utilisateurRepository.findAll();
        return utilisateurs;
    }
    @Override
    public Utilisateur addUtilisateur(UtilisateurDTO utilisateurDTO) {
        // Récupérer le mot de passe du DTO
        String rawPassword = utilisateurDTO.getPassword();
        // Chiffrer le mot de passe
        String encryptedPassword = passwordEncoder.encode(rawPassword);

        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setUsername(utilisateurDTO.getUsername());
        utilisateur.setPrenom(utilisateurDTO.getPrenom());
        utilisateur.setRole(utilisateurDTO.getRole());
        utilisateur.setNaissance(utilisateurDTO.getNaissance());
        utilisateur.setEmail(utilisateurDTO.getEmail());
        utilisateur.setNumerotel(utilisateurDTO.getNumerotel());
        utilisateur.setAdresse(utilisateurDTO.getAdresse());
        utilisateur.setPassword(encryptedPassword); // Ajouter le mot de passe chiffré
        Utilisateur savedUtilisateur = utilisateurRepository.save(utilisateur);
        // Crée l'utilisateur dans Keycloak avec le mot de passe en clair
        createUserInKeycloak(utilisateurDTO.getUsername(), rawPassword);
        return savedUtilisateur;
    }

    private Role convertStringToRole(String roleStr) {
        try {
            return Role.valueOf(roleStr.toUpperCase());
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new IllegalArgumentException("Invalid role provided: " + roleStr);
        }
    }

    @Override
    public Utilisateur updateUtilisateur(Long id, UtilisateurDTO utilisateurDTO) throws NotFoundException {
        Utilisateur utilisateur = utilisateurRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));

        utilisateur.setUsername(utilisateurDTO.getUsername());
        utilisateur.setPrenom(utilisateurDTO.getPrenom());
        utilisateur.setRole(utilisateurDTO.getRole());
        utilisateur.setNaissance(utilisateurDTO.getNaissance());
        utilisateur.setEmail(utilisateurDTO.getEmail());
        utilisateur.setNumerotel(utilisateurDTO.getNumerotel());
        utilisateur.setAdresse(utilisateurDTO.getAdresse());

        if (utilisateurDTO.getRole() != null) {
            utilisateur.setRole(utilisateurDTO.getRole());
        }

        return utilisateurRepository.save(utilisateur);
    }


    @Override
    public void deleteUtilisateur(Long id) {
        utilisateurRepository.deleteById(id);
    }


    @Override
    public Utilisateur findByEmail(String email) {
        return utilisateurRepository.findByEmail(email);
    }
    private final String keycloakBaseUrl = "http://localhost:8080";
    private final String adminRealm = "master";
    private final String realm = "realmbp";
    public String getKeycloakAdminToken() {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("grant_type", "password");
        map.add("client_id", "admin-cli");
        map.add("username", "admin");
        map.add("password", "123");

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(
                keycloakBaseUrl + "/realms/" + adminRealm + "/protocol/openid-connect/token",
                request,
                String.class
        );

        if (response.getStatusCode() == HttpStatus.OK) {
            JSONObject jsonObject = new JSONObject(response.getBody());
            return jsonObject.getString("access_token");
        } else {
            throw new RuntimeException("Failed to get Keycloak admin token: " + response.getBody());
        }
    }

    public boolean doesUserExistInKeycloak(String username) {
        String token = getKeycloakAdminToken();
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);

        HttpEntity<String> entity = new HttpEntity<>(headers);
        String email = username + "@example.com"; // Assuming the email pattern used when creating users

        String url = keycloakBaseUrl + "/admin/realms/" + realm + "/users?email=" + email;

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            JSONArray users = new JSONArray(response.getBody());
            return users.length() > 0; // User exists if the array is not empty
        } else {
            throw new RuntimeException("Failed to check if user exists in Keycloak: " + response.getBody());
        }
    }


    public void createUserInKeycloak(String username, String password) {
        // Vérifier si l'utilisateur existe déjà dans Keycloak
        if (doesUserExistInKeycloak(username)) {
            System.out.println("User with username " + username + " already exists in Keycloak.");
            return; // Ne pas continuer si l'utilisateur existe
        }

        String token = getKeycloakAdminToken();

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token);

        String email = username + "@example.com";
        JSONObject userObject = new JSONObject();
        userObject.put("username", username);
        userObject.put("email", email);
        userObject.put("firstName", username);
        userObject.put("lastName", username);
        userObject.put("enabled", true);
        userObject.put("credentials", new JSONArray().put(new JSONObject()
                .put("type", "password")
                .put("value", password)
                .put("temporary", false)));

        HttpEntity<String> entity = new HttpEntity<>(userObject.toString(), headers);
        ResponseEntity<String> response = restTemplate.postForEntity(
                keycloakBaseUrl + "/admin/realms/" + realm + "/users",
                entity,
                String.class
        );

        if (response.getStatusCode() != HttpStatus.CREATED) {
            throw new RuntimeException("Failed to create user in Keycloak: " + response.getBody());
        }
    }

    @Override
    public Utilisateur save(@NonNull Utilisateur utilisateur) {
        return utilisateurRepository.save(utilisateur);
    }

    @Override
    public Utilisateur findById(Long id) {
        return utilisateurRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

}
