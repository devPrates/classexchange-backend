package com.ClassExchange.usecases.manter_professores;

import java.time.LocalDateTime;
import java.util.UUID;

public record ProfessorResponse(
        UUID id,
        String nome,
        String email,
        String siape,
        String celular,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}