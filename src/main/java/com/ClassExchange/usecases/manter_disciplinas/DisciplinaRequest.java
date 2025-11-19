package com.ClassExchange.usecases.manter_disciplinas;

import jakarta.validation.constraints.*;

import java.util.UUID;

public record DisciplinaRequest(
        @NotBlank(message = "Nome é obrigatório")
        @Size(min = 2, max = 100, message = "Nome deve ter entre 2 e 100 caracteres")
        String nome,

        @NotNull(message = "Período é obrigatório")
        @Min(value = 1, message = "Período deve ser no mínimo 1")
        @Max(value = 20, message = "Período não pode exceder 20")
        Integer periodo,

        @NotNull(message = "Carga horária é obrigatória")
        @Positive(message = "Carga horária deve ser positiva")
        @DecimalMax(value = "999.9", message = "Carga horária não pode exceder 999.9 horas")
        Double cargaHoraria,

        @Size(max = 1000, message = "Ementa não pode exceder 1000 caracteres")
        String ementa,

        @NotNull(message = "ID do curso é obrigatório")
        UUID cursoId
) {
}