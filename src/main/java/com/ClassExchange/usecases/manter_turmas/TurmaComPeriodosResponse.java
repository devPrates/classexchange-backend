package com.ClassExchange.usecases.manter_turmas;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record TurmaComPeriodosResponse(
        UUID id,
        String nome,
        String slug,
        Integer numero,
        UUID cursoId,
        String cursoNome,
        List<com.ClassExchange.usecases.manter_periodos.PeriodoResponse> periodos,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}
