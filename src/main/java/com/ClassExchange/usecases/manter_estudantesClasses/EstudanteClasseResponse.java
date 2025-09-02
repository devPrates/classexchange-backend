package com.ClassExchange.usecases.manter_estudantesClasses;

import com.ClassExchange.domain.enums.SituacaoClasse;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record EstudanteClasseResponse(
        UUID id,
        String matricula,
        LocalDate vinculoCurso,
        SituacaoClasse situacao,
        UUID estudanteId,
        String estudanteNome,
        UUID classeId,
        String classeNome,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}