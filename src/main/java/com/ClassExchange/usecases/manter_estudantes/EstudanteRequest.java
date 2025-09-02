package com.ClassExchange.usecases.manter_estudantes;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record EstudanteRequest(

        @NotBlank(message = "O nome do estudante é obrigatório")
        @Size(min = 2, message = "O nome deve ter pelo menos 2 caracteres")
        @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres")
        String nome,

        @NotBlank(message = "O email é obrigatório")
        @Email(message = "Email deve ter um formato válido")
        @Size(max = 150, message = "O email deve ter no máximo 150 caracteres")
        String email

) {}