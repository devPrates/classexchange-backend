package com.ClassExchange.usecases.manter_aulas;

import java.time.LocalDateTime;
import java.util.UUID;

public record AulaResponse(
        UUID id,
        UUID periodoId,
        String periodoNome,
        UUID disciplinaId,
        String disciplinaNome,
        UUID professorId,
        String professorNome,
        UUID localId,
        String localNome,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}

