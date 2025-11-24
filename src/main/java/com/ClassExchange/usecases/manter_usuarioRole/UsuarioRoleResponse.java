package com.ClassExchange.usecases.manter_usuarioRole;

import java.time.LocalDateTime;
import java.util.UUID;

public record UsuarioRoleResponse(
        UUID id,
        UUID usuarioId,
        String usuarioNome,
        String usuarioEmail,
        UUID roleId,
        String roleNome,
        String roleDescricao,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}

