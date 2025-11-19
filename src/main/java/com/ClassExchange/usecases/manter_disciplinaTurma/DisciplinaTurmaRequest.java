package com.ClassExchange.usecases.manter_disciplinaTurma;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record DisciplinaTurmaRequest(
        @NotNull(message = "ID da disciplina é obrigatório")
        UUID disciplinaId,

        @NotNull(message = "ID da turma é obrigatório")
        UUID turmaId,

        @NotNull(message = "ID do local é obrigatório")
        UUID localId
) {}