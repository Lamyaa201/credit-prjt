package net.nak.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
public class CustomAuthenticationEntryPoint extends LoginUrlAuthenticationEntryPoint {
    public CustomAuthenticationEntryPoint(String loginFormUrl) {
        super(loginFormUrl);  // Ensure this does not cause a loop
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        // You can customize response here instead of redirecting
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.getWriter().write("{\"error\": \"Unauthorized\"}");
    }
}