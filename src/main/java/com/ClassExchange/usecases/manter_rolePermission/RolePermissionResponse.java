package com.ClassExchange.usecases.manter_rolePermission;

import java.time.LocalDateTime;
import java.util.UUID;

public record RolePermissionResponse(
        UUID id,
        UUID roleId,
        String roleNome,
        UUID permissionId,
        String permissionNome,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}

