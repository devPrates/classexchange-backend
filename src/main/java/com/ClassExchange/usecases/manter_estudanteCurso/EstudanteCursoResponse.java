package com.ClassExchange.usecases.manter_estudanteCurso;

import com.ClassExchange.domain.enums.SituacaoClasse;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record EstudanteCursoResponse(
        UUID id,
        String matricula,
        LocalDate vinculoCurso,
        SituacaoClasse situacao,
        UUID estudanteId,
        String estudanteNome,
        UUID cursoId,
        String cursoNome,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}