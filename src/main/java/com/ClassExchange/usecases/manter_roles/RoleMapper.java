package com.ClassExchange.usecases.manter_roles;

import com.ClassExchange.domain.entity.Role;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper {

    public RoleResponse toResponse(Role role) {
        return new RoleResponse(
                role.getId(),
                role.getNome(),
                role.getDescricao(),
                role.getCreatedAt(),
                role.getUpdatedAt()
        );
    }

    public Role toEntity(RoleRequest request) {
        Role role = new Role();
        role.setNome(request.nome());
        role.setDescricao(request.descricao());
        return role;
    }

    public void updateEntityFromRequest(RoleRequest request, Role role) {
        role.setNome(request.nome());
        role.setDescricao(request.descricao());
    }
}

