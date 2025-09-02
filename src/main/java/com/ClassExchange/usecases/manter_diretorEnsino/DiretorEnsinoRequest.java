package com.ClassExchange.usecases.manter_diretorEnsino;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public record DiretorEnsinoRequest(
        @NotNull(message = "Data de início é obrigatória")
        LocalDate inicio,

        LocalDate fim,

        @NotNull(message = "ID do professor é obrigatório")
        UUID professorId,

        @NotNull(message = "ID do campus é obrigatório")
        UUID campusId
) {
}