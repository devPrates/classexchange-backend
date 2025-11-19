package com.ClassExchange.usecases.manter_periodos;

import com.ClassExchange.domain.enums.TipoPeriodo;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.UUID;

public record PeriodoRequest(
        @NotBlank(message = "Nome é obrigatório")
        @Size(max = 100, message = "Nome deve ter no máximo 100 caracteres")
        String nome,

        @NotNull(message = "Tipo do período é obrigatório")
        TipoPeriodo tipoPeriodo,

        @NotNull(message = "Número é obrigatório")
        @Positive(message = "Número deve ser positivo")
        Integer numero,

        @NotNull(message = "Ano é obrigatório")
        @Min(value = 2000, message = "Ano deve ser maior ou igual a 2000")
        @Max(value = 2100, message = "Ano deve ser menor ou igual a 2100")
        Integer ano,

        @NotNull(message = "Data de início é obrigatória")
        @Future(message = "Data de início deve ser futura")
        LocalDate inicio,

        @NotNull(message = "Data de fim é obrigatória")
        @Future(message = "Data de fim deve ser futura")
        LocalDate fim,

        

        @NotNull(message = "ID da turma é obrigatório")
        UUID turmaId
) {
}