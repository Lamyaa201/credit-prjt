package net.nak.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;

@Configuration
public class JwtConfig {

    @Value("${keycloak.auth-server-url}")
    private String keycloakServerUrl;

    @Value("${keycloak.realm}")
    private String realm;

    @Bean
    public JwtDecoder jwtDecoder() {
        String jwkSetUri = String.format("%s/realms/%s/protocol/openid-connect/certs", keycloakServerUrl, realm);
        return NimbusJwtDecoder.withJwkSetUri(jwkSetUri).build();
    }
}
