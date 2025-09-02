package com.ClassExchange.usecases.manter_periodos;

import com.ClassExchange.domain.enums.TipoPeriodo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record PeriodoResponse(
        UUID id,
        String nome,
        TipoPeriodo tipoPeriodo,
        Integer numero,
        Integer ano,
        LocalDate inicio,
        LocalDate fim,
        UUID disciplinaId,
        String disciplinaNome,
        UUID turmaId,
        String turmaNome,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}