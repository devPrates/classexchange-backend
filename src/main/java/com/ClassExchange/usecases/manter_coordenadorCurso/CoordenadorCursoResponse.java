package com.ClassExchange.usecases.manter_coordenadorCurso;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record CoordenadorCursoResponse(
        UUID id,
        LocalDate inicio,
        LocalDate fim,
        UUID professorId,
        String professorNome,
        UUID cursoId,
        String cursoNome,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}