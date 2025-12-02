package com.ClassExchange.security;

import com.ClassExchange.usecases.manter_usuarios.UsuarioRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
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

    public GoogleOAuth2SuccessHandler(UsuarioRepository usuarioRepository, JwtService jwtService) {
        this.usuarioRepository = usuarioRepository;
        this.jwtService = jwtService;
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
            writeJson(response, 403, Map.of("error", "Conta não encontrada"));
            return;
        }

        var usuarioOpt = usuarioRepository.findByEmail(email);
        if (usuarioOpt.isEmpty()) {
            writeJson(response, 403, Map.of("error", "Conta não encontrada. Procure um administrador."));
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
        writeJson(response, 200, Map.of("token", token));
    }

    private void writeJson(HttpServletResponse response, int status, Map<String, Object> body) throws IOException {
        response.setStatus(status);
        response.setContentType("application/json");
        StringBuilder sb = new StringBuilder("{");
        int i = 0;
        for (var e : body.entrySet()) {
            if (i++ > 0) sb.append(',');
            sb.append('"').append(e.getKey()).append('"').append(':');
            Object v = e.getValue();
            if (v == null) {
                sb.append("null");
            } else {
                sb.append('"').append(v.toString().replace("\"", "\\\"")).append('"');
            }
        }
        sb.append('}');
        response.getOutputStream().write(sb.toString().getBytes(StandardCharsets.UTF_8));
    }
}

