package com.ClassExchange.usecases.manter_professorClasse;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public record ProfessorClasseRequest(
        @NotNull(message = "Data de início é obrigatória")
        LocalDate inicio,

        LocalDate fim,

        @NotNull(message = "ID do professor é obrigatório")
        UUID professorId,

        @NotNull(message = "ID da classe é obrigatório")
        UUID classeId
) {
}