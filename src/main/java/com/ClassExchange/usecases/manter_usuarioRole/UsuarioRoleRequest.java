package com.ClassExchange.usecases.manter_usuarioRole;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record UsuarioRoleRequest(
        @NotNull(message = "ID do usuário é obrigatório")
        UUID usuarioId,
        @NotNull(message = "ID do role é obrigatório")
        UUID roleId
) {}

