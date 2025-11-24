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
        List<TurmaSimplificada> turmas,
        CoordenadorSimplificado coordenadorCurso,
        long professoresCount,
        long studentsCount,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public record TurmaSimplificada(
            UUID id,
            String nome,
            Integer numero
    ) {}

    public record ProfessorCursoSimplificado(
            UUID id,
            UUID usuarioId,
            String usuarioNome,
            String usuarioEmail,
            String usuarioSiape
    ) {}

    public record CoordenadorSimplificado(
            UUID id,
            UUID usuarioId,
            String usuarioNome,
            java.time.LocalDate inicio,
            java.time.LocalDate fim
    ) {}

    public record EstudanteSimplificado(
            UUID id,
            String nome,
            String email,
            String matricula,
            com.ClassExchange.domain.enums.SituacaoClasse situacao,
            java.time.LocalDate vinculoCurso
    ) {}
}
