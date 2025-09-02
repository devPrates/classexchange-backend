package com.ClassExchange.usecases.manter_estudantesClasses;

import com.ClassExchange.domain.enums.SituacaoClasse;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.UUID;

public record EstudanteClasseRequest(

        @NotBlank(message = "A matrícula é obrigatória")
        @Size(min = 5, message = "A matrícula deve ter pelo menos 5 caracteres")
        @Size(max = 20, message = "A matrícula deve ter no máximo 20 caracteres")
        String matricula,

        @NotNull(message = "A data de vínculo com o curso é obrigatória")
        LocalDate vinculoCurso,

        @NotNull(message = "A situação da classe é obrigatória")
        SituacaoClasse situacao,

        @NotNull(message = "O ID do estudante é obrigatório")
        UUID estudanteId,

        @NotNull(message = "O ID da classe é obrigatório")
        UUID classeId

) {
}