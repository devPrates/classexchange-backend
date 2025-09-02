package com.ClassExchange.usecases.manter_classes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record ClasseResponse(
        UUID id,
        Integer vagas,
        Integer numeroAulas,
        LocalDate inicio,
        UUID disciplinaId,
        String disciplinaNome,
        UUID turmaId,
        String turmaNome,
        UUID cargaHorariaId,
        String cargaHorariaNome,
        UUID localId,
        String localNome,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}