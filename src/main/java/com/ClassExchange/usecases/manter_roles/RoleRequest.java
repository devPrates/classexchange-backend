package com.ClassExchange.usecases.manter_roles;

import jakarta.validation.constraints.NotBlank;

public record RoleRequest(
        @NotBlank(message = "Nome é obrigatório")
        String nome,
        String descricao
) {}

