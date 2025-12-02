package com.ClassExchange.usecases.auth;

import com.ClassExchange.security.JwtService;
import com.ClassExchange.usecases.manter_usuarios.UsuarioRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    public record LoginRequest(
            @Email @NotBlank String email,
            @NotBlank String senha
    ) {}

    public record LoginResponse(String token) {}

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthController(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        var usuarioOpt = usuarioRepository.findByEmail(request.email());
        if (usuarioOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        var usuario = usuarioOpt.get();
        if (!passwordEncoder.matches(request.senha(), usuario.getSenha())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", usuario.getId().toString());
        claims.put("nome", usuario.getNome());
        claims.put("email", usuario.getEmail());
        claims.put("campus_id", usuario.getCampus() != null ? usuario.getCampus().getId().toString() : null);
        claims.put("roles", List.of(usuario.getRole().name()));
        String token = jwtService.generateToken(usuario.getId().toString(), claims, 3600);
        return ResponseEntity.ok(new LoginResponse(token));
    }

    @GetMapping("/oauth2/google")
    public ResponseEntity<Void> oauth2Google() {
        return ResponseEntity.status(HttpStatus.FOUND)
                .header("Location", "/oauth2/authorization/google")
                .build();
    }
}

