package com.ClassExchange.usecases.manter_professorCurso;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record ProfessorCursoRequest(
        @NotNull(message = "ID do usuário é obrigatório")
        UUID usuarioId,

        @NotNull(message = "ID do curso é obrigatório")
        UUID cursoId
) {}