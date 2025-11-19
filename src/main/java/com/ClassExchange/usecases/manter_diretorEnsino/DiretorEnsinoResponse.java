package com.ClassExchange.usecases.manter_diretorEnsino;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record DiretorEnsinoResponse(
        UUID id,
        LocalDate inicio,
        LocalDate fim,
        UUID usuarioId,
        String usuarioNome,
        UUID campusId,
        String campusNome,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}