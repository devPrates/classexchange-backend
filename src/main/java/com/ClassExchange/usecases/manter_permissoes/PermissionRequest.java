package com.ClassExchange.usecases.manter_permissoes;

import jakarta.validation.constraints.NotBlank;

public record PermissionRequest(
        @NotBlank(message = "Nome é obrigatório")
        String nome,
        String descricao
) {}

