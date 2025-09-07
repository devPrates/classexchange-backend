package com.ClassExchange.usecases.manter_campus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record CampusResponse(
        UUID id,
        String nome,
        String sigla,
        String email,
        List<CursoSimplificadoResponse> cursos,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}