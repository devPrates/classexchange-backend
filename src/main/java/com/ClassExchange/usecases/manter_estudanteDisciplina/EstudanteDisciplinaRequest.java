package com.ClassExchange.usecases.manter_estudanteDisciplina;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record EstudanteDisciplinaRequest(

        @NotNull(message = "ID do vínculo estudante-curso é obrigatório")
        UUID estudanteCursoId,

        @NotNull(message = "ID da aula é obrigatório")
        UUID aulaId
) {}
