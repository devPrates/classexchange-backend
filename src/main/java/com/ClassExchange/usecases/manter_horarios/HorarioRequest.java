package com.ClassExchange.usecases.manter_horarios;

import jakarta.validation.constraints.*;

import java.util.UUID;

public record HorarioRequest(
        @NotNull(message = "Dia da semana é obrigatório")
        com.ClassExchange.domain.enums.DiaSemana diaDaSemana,

        @NotBlank(message = "Hora de início é obrigatória")
        @Pattern(regexp = "^([01]?[0-9]|2[0-3]):[0-5][0-9]$", message = "Hora de início deve estar no formato HH:mm")
        String horaInicio,

        @NotBlank(message = "Hora de fim é obrigatória")
        @Pattern(regexp = "^([01]?[0-9]|2[0-3]):[0-5][0-9]$", message = "Hora de fim deve estar no formato HH:mm")
        String horaFim,

        @NotNull(message = "ID da aula é obrigatório")
        UUID aulaId
) {}
