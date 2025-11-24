package com.ClassExchange.usecases.manter_usuarioRole;

import com.ClassExchange.domain.entity.Role;
import com.ClassExchange.domain.entity.Usuario;
import com.ClassExchange.domain.entity.UsuarioRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UsuarioRoleRepository extends JpaRepository<UsuarioRole, UUID> {
    Optional<UsuarioRole> findByUsuarioAndRole(Usuario usuario, Role role);
}

