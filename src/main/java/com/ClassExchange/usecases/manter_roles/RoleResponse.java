package com.ClassExchange.usecases.manter_roles;

import java.time.LocalDateTime;
import java.util.UUID;

public record RoleResponse(
        UUID id,
        String nome,
        String descricao,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}

