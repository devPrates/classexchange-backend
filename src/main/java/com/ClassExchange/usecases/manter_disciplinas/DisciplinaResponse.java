package com.ClassExchange.usecases.manter_disciplinas;

import java.time.LocalDateTime;
import java.util.UUID;

public record DisciplinaResponse(
        UUID id,
        String nome,
        Double cargahoraria,
        String ementa,
        UUID cursoId,
        String cursoNome,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}