package com.detech.gsrt.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

public class JwtRequestFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String path = request.getRequestURI();

        if (path.equals("/authenticate")) {
            // 💥 Cette vérification doit exister
            filterChain.doFilter(request, response);
            return;
        }

        // Vérification JWT sinon
        String token = extractToken(request);
        if (token == null || !isValidToken(token)) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        // Continuer la chaîne
        filterChain.doFilter(request, response);
    }

    private String extractToken(HttpServletRequest request) {
        // Cherche le header Authorization: Bearer <token>
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7); // Retire "Bearer "
        }
        return null;
    }

    private boolean isValidToken(String token) {

        return true;
    }
}
