package com.ClassExchange.usecases.manter_usuarioRole;

import com.ClassExchange.domain.entity.Role;
import com.ClassExchange.domain.entity.Usuario;
import com.ClassExchange.domain.entity.UsuarioRole;
import org.springframework.stereotype.Component;

@Component
public class UsuarioRoleMapper {

    public UsuarioRoleResponse toResponse(UsuarioRole ur) {
        return new UsuarioRoleResponse(
                ur.getId(),
                ur.getUsuario() != null ? ur.getUsuario().getId() : null,
                ur.getUsuario() != null ? ur.getUsuario().getNome() : null,
                ur.getUsuario() != null ? ur.getUsuario().getEmail() : null,
                ur.getRole() != null ? ur.getRole().getId() : null,
                ur.getRole() != null ? ur.getRole().getNome() : null,
                ur.getRole() != null ? ur.getRole().getDescricao() : null,
                ur.getCreatedAt(),
                ur.getUpdatedAt()
        );
    }

    public UsuarioRole toEntity(UsuarioRoleRequest request, Usuario usuario, Role role) {
        UsuarioRole ur = new UsuarioRole();
        ur.setUsuario(usuario);
        ur.setRole(role);
        return ur;
    }

    public void updateEntityFromRequest(UsuarioRoleRequest request, UsuarioRole ur, Usuario usuario, Role role) {
        ur.setUsuario(usuario);
        ur.setRole(role);
    }
}

