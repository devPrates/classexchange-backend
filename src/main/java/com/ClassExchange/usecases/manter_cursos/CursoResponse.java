package com.ClassExchange.usecases.manter_cursos;

import java.time.LocalDateTime;
import java.util.UUID;

public record CursoResponse(
        UUID id,
        String nome,
        String sigla,
        UUID campusId,
        String campusNome,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}