package com.ClassExchange.usecases.manter_campus;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CampusRequest(

        @NotBlank(message = "O nome do campus é obrigatório")
        @Size(min = 3, message = "O nome deve ter pelo menos 3 caracteres")
        String nome,

        @NotBlank(message = "A sigla do campus é obrigatória")
        @Size(max = 10, message = "A sigla deve ter no máximo 10 caracteres")
        String sigla,

        @NotBlank(message = "O e-mail é obrigatório")
        @Email(message = "O e-mail informado não é válido")
        String email
) {}