package com.ClassExchange.usecases.manter_estudanteDisciplina;

import com.ClassExchange.domain.enums.SituacaoClasse;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record EstudanteDisciplinaResponse(
        UUID id,
        String matricula,
        LocalDate vinculoCurso,
        SituacaoClasse situacao,
        UUID estudanteId,
        String estudanteNome,
        UUID disciplinaTurmaId,
        String disciplinaNome,
        String turmaNome,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}