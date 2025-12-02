package com.ClassExchange.security;

import com.ClassExchange.usecases.manter_usuarios.UsuarioRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class GoogleOAuth2SuccessHandler implements AuthenticationSuccessHandler {

    private final UsuarioRepository usuarioRepository;
    private final JwtService jwtService;
    private final String frontendUrl;

    public GoogleOAuth2SuccessHandler(UsuarioRepository usuarioRepository, JwtService jwtService, @Value("${app.frontend.url:http://localhost:3000}") String frontendUrl) {
        this.usuarioRepository = usuarioRepository;
        this.jwtService = jwtService;
        this.frontendUrl = frontendUrl;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        Object principal = authentication.getPrincipal();
        String email = null;
        if (principal instanceof DefaultOAuth2User user) {
            Object em = user.getAttributes().get("email");
            email = em != null ? em.toString() : null;
        }

        if (email == null) {
            response.sendRedirect(frontendUrl + "/login?error=" + java.net.URLEncoder.encode("Conta não encontrada", java.nio.charset.StandardCharsets.UTF_8));
            return;
        }

        var usuarioOpt = usuarioRepository.findByEmail(email);
        if (usuarioOpt.isEmpty()) {
            response.sendRedirect(frontendUrl + "/login?error=" + java.net.URLEncoder.encode("Conta não encontrada. Procure um administrador.", java.nio.charset.StandardCharsets.UTF_8));
            return;
        }

        var usuario = usuarioOpt.get();
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", usuario.getId().toString());
        claims.put("nome", usuario.getNome());
        claims.put("email", usuario.getEmail());
        claims.put("campus_id", usuario.getCampus() != null ? usuario.getCampus().getId().toString() : null);
        claims.put("roles", List.of(usuario.getRole().name()));
        String token = jwtService.generateToken(usuario.getId().toString(), claims, 3600);
        response.sendRedirect(frontendUrl + "/login/callback?token=" + java.net.URLEncoder.encode(token, java.nio.charset.StandardCharsets.UTF_8));
    }
    
}

