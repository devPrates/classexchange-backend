package com.ClassExchange.usecases.manter_horarios;

import java.time.LocalDateTime;
import java.util.UUID;

public record HorarioResponse(
        UUID id,
        Integer diaDaSemana,
        String horaInicio,
        String horaFim,
        UUID disciplinaTurmaId,
        String disciplinaNome,
        String turmaNome,
        UUID cargaHorariaId,
        String cargaHorariaNome,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}