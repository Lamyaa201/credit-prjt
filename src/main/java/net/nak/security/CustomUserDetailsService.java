package net.nak.security;

import net.nak.entities.Utilisateur;
import net.nak.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Utilisateur utilis = utilisateurRepository.findByUsername(username);
        if (utilis == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        // Vérifier si le rôle de l'utilisateur est null
        if (utilis.getRole() == null) {
            throw new IllegalStateException("User " + username + " does not have a role assigned");
        }

        return new User(username, "", true, true, true, true,
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + utilis.getRole().name())));
    }
}
