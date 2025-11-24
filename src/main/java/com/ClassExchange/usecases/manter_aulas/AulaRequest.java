package com.ClassExchange.usecases.manter_aulas;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record AulaRequest(
        @NotNull(message = "ID do período é obrigatório")
        UUID periodoId,

        @NotNull(message = "ID da disciplina é obrigatório")
        UUID disciplinaId,

        @NotNull(message = "ID do professor é obrigatório")
        UUID professorId,

        @NotNull(message = "ID do local é obrigatório")
        UUID localId
) {}

