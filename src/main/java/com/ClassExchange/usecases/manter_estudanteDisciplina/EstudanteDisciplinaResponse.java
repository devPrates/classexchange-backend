package com.ClassExchange.usecases.manter_estudanteDisciplina;

import java.time.LocalDateTime;
import java.util.UUID;

public record EstudanteDisciplinaResponse(
        UUID id,
        UUID estudanteCursoId,
        String cursoNome,
        String estudanteNome,
        UUID aulaId,
        String periodoNome,
        String disciplinaNome,
        String professorNome,
        String localNome,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}
