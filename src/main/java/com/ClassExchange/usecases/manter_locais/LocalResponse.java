package com.ClassExchange.usecases.manter_locais;

import java.time.LocalDateTime;
import java.util.UUID;

public record LocalResponse(
        UUID id,
        String nome,
        UUID campusId,
        String campusNome,
        Integer capacidade,
        String bloco,
        String andar,
        String tipo,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}
