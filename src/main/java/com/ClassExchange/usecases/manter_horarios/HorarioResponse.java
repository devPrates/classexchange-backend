package com.ClassExchange.usecases.manter_horarios;

import java.time.LocalDateTime;
import java.util.UUID;

public record HorarioResponse(
        UUID id,
        com.ClassExchange.domain.enums.DiaSemana diaDaSemana,
        String horaInicio,
        String horaFim,
        UUID aulaId,
        UUID periodoId,
        String periodoNome,
        UUID disciplinaId,
        String disciplinaNome,
        UUID professorId,
        String professorNome,
        UUID localId,
        String localNome,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}
