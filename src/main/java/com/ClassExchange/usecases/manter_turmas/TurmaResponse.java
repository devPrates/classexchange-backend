package com.ClassExchange.usecases.manter_turmas;

import java.time.LocalDateTime;
import java.util.UUID;

public record TurmaResponse(
        UUID id,
        String nome,
        Integer numero,
        UUID cursoId,
        String cursoNome,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}