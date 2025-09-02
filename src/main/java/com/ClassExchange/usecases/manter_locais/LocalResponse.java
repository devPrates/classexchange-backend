package com.ClassExchange.usecases.manter_locais;

import java.time.LocalDateTime;
import java.util.UUID;

public record LocalResponse(
        UUID id,
        String nome,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}