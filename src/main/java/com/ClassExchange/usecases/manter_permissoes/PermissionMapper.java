package com.ClassExchange.usecases.manter_permissoes;

import com.ClassExchange.domain.entity.Permission;
import org.springframework.stereotype.Component;

@Component
public class PermissionMapper {

    public PermissionResponse toResponse(Permission permission) {
        return new PermissionResponse(
                permission.getId(),
                permission.getNome(),
                permission.getDescricao(),
                permission.getCreatedAt(),
                permission.getUpdatedAt()
        );
    }

    public Permission toEntity(PermissionRequest request) {
        Permission p = new Permission();
        p.setNome(request.nome());
        p.setDescricao(request.descricao());
        return p;
    }

    public void updateEntityFromRequest(PermissionRequest request, Permission permission) {
        permission.setNome(request.nome());
        permission.setDescricao(request.descricao());
    }
}

