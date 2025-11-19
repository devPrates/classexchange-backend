package com.ClassExchange.usecases.manter_horarios;

import jakarta.validation.constraints.*;

import java.util.UUID;

public record HorarioRequest(
        @NotNull(message = "Dia da semana é obrigatório")
        @Min(value = 1, message = "Dia da semana deve ser entre 1 e 7")
        @Max(value = 7, message = "Dia da semana deve ser entre 1 e 7")
        Integer diaDaSemana,

        @NotBlank(message = "Hora de início é obrigatória")
        @Pattern(regexp = "^([01]?[0-9]|2[0-3]):[0-5][0-9]$", message = "Hora de início deve estar no formato HH:mm")
        String horaInicio,

        @NotBlank(message = "Hora de fim é obrigatória")
        @Pattern(regexp = "^([01]?[0-9]|2[0-3]):[0-5][0-9]$", message = "Hora de fim deve estar no formato HH:mm")
        String horaFim,

        @NotNull(message = "ID da disciplina/turma é obrigatório")
        UUID disciplinaTurmaId,

        @NotNull(message = "ID da carga horária é obrigatório")
        UUID cargaHorariaId
) {}