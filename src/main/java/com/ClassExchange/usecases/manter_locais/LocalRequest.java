package com.ClassExchange.usecases.manter_locais;

import jakarta.validation.constraints.*;

import java.util.UUID;

public record LocalRequest(

        @NotBlank(message = "O nome do local é obrigatório")
        @Size(min = 3, message = "O nome deve ter pelo menos 3 caracteres")
        @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres")
        String nome,

        @NotNull(message = "ID do campus é obrigatório")
        UUID campusId,

        @Min(value = 0, message = "Capacidade não pode ser negativa")
        Integer capacidade,

        @Size(max = 50, message = "Bloco deve ter no máximo 50 caracteres")
        String bloco,

        @Size(max = 50, message = "Andar deve ter no máximo 50 caracteres")
        String andar,

        @Size(max = 50, message = "Tipo deve ter no máximo 50 caracteres")
        String tipo
) {}
