package com.ClassExchange.usecases.manter_professorCurso;

import java.time.LocalDateTime;
import java.util.UUID;

public record ProfessorCursoResponse(
        UUID id,
        UUID usuarioId,
        String usuarioNome,
        UUID cursoId,
        String cursoNome,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}