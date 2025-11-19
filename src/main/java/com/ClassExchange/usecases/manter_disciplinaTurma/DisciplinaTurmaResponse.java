package com.ClassExchange.usecases.manter_disciplinaTurma;

import java.time.LocalDateTime;
import java.util.UUID;

public record DisciplinaTurmaResponse(
        UUID id,
        UUID disciplinaId,
        String disciplinaNome,
        UUID turmaId,
        String turmaNome,
        UUID localId,
        String localNome,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}