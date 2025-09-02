package com.ClassExchange.usecases.manter_turmas;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.util.UUID;

public record TurmaRequest(

        @NotBlank(message = "O nome da turma é obrigatório")
        @Size(min = 3, message = "O nome deve ter pelo menos 3 caracteres")
        String nome,

        @NotNull(message = "O número da turma é obrigatório")
        @Positive(message = "O número da turma deve ser positivo")
        Integer numero,

        @NotNull(message = "O curso é obrigatório")
        UUID cursoId
) {}