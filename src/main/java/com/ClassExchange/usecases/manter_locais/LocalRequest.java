package com.ClassExchange.usecases.manter_locais;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LocalRequest(

        @NotBlank(message = "O nome do local é obrigatório")
        @Size(min = 3, message = "O nome deve ter pelo menos 3 caracteres")
        @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres")
        String nome
) {}