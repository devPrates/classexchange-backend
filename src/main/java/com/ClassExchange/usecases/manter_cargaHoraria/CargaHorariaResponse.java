package com.ClassExchange.usecases.manter_cargaHoraria;

import com.ClassExchange.domain.enums.MedidaTempo;

import java.time.LocalDateTime;
import java.util.UUID;

public record CargaHorariaResponse(
        UUID id,
        String nome,
        Integer duracao,
        MedidaTempo medidaTempo,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}