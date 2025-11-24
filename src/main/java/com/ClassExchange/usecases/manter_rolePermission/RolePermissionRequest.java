package com.ClassExchange.usecases.manter_rolePermission;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record RolePermissionRequest(
        @NotNull(message = "ID do role é obrigatório")
        UUID roleId,
        @NotNull(message = "ID da permissão é obrigatório")
        UUID permissionId
) {}

