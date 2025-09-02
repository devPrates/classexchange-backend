package com.ClassExchange.usecases.manter_classes;

import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.UUID;

public record ClasseRequest(
        @NotNull(message = "Número de vagas é obrigatório")
        @Positive(message = "Número de vagas deve ser positivo")
        @Max(value = 1000, message = "Número de vagas não pode exceder 1000")
        Integer vagas,

        @NotNull(message = "Número de aulas é obrigatório")
        @Positive(message = "Número de aulas deve ser positivo")
        @Max(value = 200, message = "Número de aulas não pode exceder 200")
        Integer numeroAulas,

        @NotNull(message = "Data de início é obrigatória")
        @Future(message = "Data de início deve ser futura")
        LocalDate inicio,

        @NotNull(message = "ID da disciplina é obrigatório")
        UUID disciplinaId,

        @NotNull(message = "ID da turma é obrigatório")
        UUID turmaId,

        @NotNull(message = "ID da carga horária é obrigatório")
        UUID cargaHorariaId,

        @NotNull(message = "ID do local é obrigatório")
        UUID localId
) {
}