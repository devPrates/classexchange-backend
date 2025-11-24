package com.ClassExchange.usecases.manter_rolePermission;

import com.ClassExchange.domain.entity.Permission;
import com.ClassExchange.domain.entity.Role;
import com.ClassExchange.domain.entity.RolePermission;
import org.springframework.stereotype.Component;

@Component
public class RolePermissionMapper {

    public RolePermissionResponse toResponse(RolePermission rp) {
        return new RolePermissionResponse(
                rp.getId(),
                rp.getRole() != null ? rp.getRole().getId() : null,
                rp.getRole() != null ? rp.getRole().getNome() : null,
                rp.getPermission() != null ? rp.getPermission().getId() : null,
                rp.getPermission() != null ? rp.getPermission().getNome() : null,
                rp.getCreatedAt(),
                rp.getUpdatedAt()
        );
    }

    public RolePermission toEntity(RolePermissionRequest request, Role role, Permission permission) {
        RolePermission rp = new RolePermission();
        rp.setRole(role);
        rp.setPermission(permission);
        return rp;
    }

    public void updateEntityFromRequest(RolePermissionRequest request, RolePermission rp, Role role, Permission permission) {
        rp.setRole(role);
        rp.setPermission(permission);
    }
}

