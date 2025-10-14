package com.ClassExchange.usecases.manter_cursos;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record CursoResponse(
        UUID id,
        String nome,
        String sigla,
        String slug,
        UUID campusId,
        String campusNome,
        List<DisciplinaSimplificada> disciplinas,
        List<TurmaSimplificada> turmas,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public record DisciplinaSimplificada(
            UUID id,
            String nome,
            double cargaHoraria
    ) {}

    public record TurmaSimplificada(
            UUID id,
            String nome
    ) {}
}