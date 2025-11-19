package com.ClassExchange.usecases.manter_professorDisciplina;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record ProfessorDisciplinaResponse(
        UUID id,
        LocalDate inicio,
        LocalDate fim,
        UUID usuarioId,
        String usuarioNome,
        UUID disciplinaTurmaId,
        String disciplinaNome,
        String turmaNome,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}