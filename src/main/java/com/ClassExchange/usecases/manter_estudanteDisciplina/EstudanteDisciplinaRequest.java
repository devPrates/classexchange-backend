package com.ClassExchange.usecases.manter_estudanteDisciplina;

import com.ClassExchange.domain.enums.SituacaoClasse;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.UUID;

public record EstudanteDisciplinaRequest(
        @NotBlank(message = "Matrícula é obrigatória")
        @Size(min = 3, max = 30, message = "Matrícula deve ter entre 3 e 30 caracteres")
        String matricula,

        @NotNull(message = "Data de vínculo ao curso é obrigatória")
        LocalDate vinculoCurso,

        @NotNull(message = "Situação é obrigatória")
        SituacaoClasse situacao,

        @NotNull(message = "ID do estudante é obrigatório")
        UUID estudanteId,

        @NotNull(message = "ID da disciplina/turma é obrigatório")
        UUID disciplinaTurmaId
) {}