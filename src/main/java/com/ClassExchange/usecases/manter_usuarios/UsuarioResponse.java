package com.ClassExchange.usecases.manter_usuarios;

import com.ClassExchange.domain.enums.RoleUsuario;
import java.time.LocalDateTime;
import java.util.UUID;

public record UsuarioResponse(
        UUID id,
        String nome,
        String email,
        String celular,
        RoleUsuario role,
        UUID campusId,
        String campusNome,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}