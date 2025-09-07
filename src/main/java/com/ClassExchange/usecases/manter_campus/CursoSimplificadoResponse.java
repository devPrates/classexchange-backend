package com.ClassExchange.usecases.manter_campus;

import java.util.UUID;

public record CursoSimplificadoResponse(
        UUID id,
        String nome,
        String sigla
) {}