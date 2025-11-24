package com.ClassExchange.usecases.manter_permissoes;

import java.time.LocalDateTime;
import java.util.UUID;

public record PermissionResponse(
        UUID id,
        String nome,
        String descricao,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}

