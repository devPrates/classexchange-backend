package com.ClassExchange.usecases.manter_professorDisciplina;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public record ProfessorDisciplinaRequest(
        @NotNull(message = "Data de início é obrigatória")
        LocalDate inicio,

        LocalDate fim,

        @NotNull(message = "ID do usuário é obrigatório")
        UUID usuarioId,

        @NotNull(message = "ID da disciplina/turma é obrigatório")
        UUID disciplinaTurmaId
) {}