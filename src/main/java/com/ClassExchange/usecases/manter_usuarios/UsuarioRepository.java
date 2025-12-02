package com.ClassExchange.usecases.manter_usuarios;

import com.ClassExchange.domain.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {
    List<Usuario> findByCampusId(UUID campusId);
    long countByCampusId(UUID campusId);
    java.util.Optional<Usuario> findByEmail(String email);
}
