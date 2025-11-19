package com.ClassExchange.usecases.manter_coordenadorCurso;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public record CoordenadorCursoRequest(
        @NotNull(message = "Data de início é obrigatória")
        LocalDate inicio,

        LocalDate fim,

        @NotNull(message = "ID do usuário é obrigatório")
        UUID usuarioId,

        @NotNull(message = "ID do curso é obrigatório")
        UUID cursoId
) {
}