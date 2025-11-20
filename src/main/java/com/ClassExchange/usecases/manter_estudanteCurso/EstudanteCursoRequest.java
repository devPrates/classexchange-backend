package com.ClassExchange.usecases.manter_estudanteCurso;

import com.ClassExchange.domain.enums.SituacaoClasse;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public record EstudanteCursoRequest(
        @NotNull UUID estudanteId,
        @NotNull UUID cursoId,
        LocalDate vinculoCurso,
        SituacaoClasse situacao
) {}