package com.ClassExchange.usecases.manter_periodos;

import com.ClassExchange.domain.enums.TipoPeriodo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record PeriodoCursoResponse(
        UUID id,
        String nome,
        String slug,
        TipoPeriodo tipoPeriodo,
        Integer numero,
        Integer ano,
        LocalDate inicio,
        LocalDate fim,
        List<DisciplinaSimplificada> disciplinas,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public record DisciplinaSimplificada(
            UUID id,
            String nome,
            String slug
    ) {}
}