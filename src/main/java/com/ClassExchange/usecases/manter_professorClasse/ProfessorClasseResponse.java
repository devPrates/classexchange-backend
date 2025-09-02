package com.ClassExchange.usecases.manter_professorClasse;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record ProfessorClasseResponse(
        UUID id,
        LocalDate inicio,
        LocalDate fim,
        UUID professorId,
        String professorNome,
        UUID classeId,
        String classeNome,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}