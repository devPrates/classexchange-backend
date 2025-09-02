package com.ClassExchange.usecases.manter_estudantes;

import java.time.LocalDateTime;
import java.util.UUID;

public record EstudanteResponse(
        UUID id,
        String nome,
        String email,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}