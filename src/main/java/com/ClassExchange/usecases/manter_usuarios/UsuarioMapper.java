package com.ClassExchange.usecases.manter_usuarios;

import com.ClassExchange.domain.entity.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {

    public UsuarioResponse toResponse(Usuario usuario) {
        return new UsuarioResponse(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getCelular(),
                usuario.getRole(),
                usuario.getCampus() != null ? usuario.getCampus().getId() : null,
                usuario.getCampus() != null ? usuario.getCampus().getNome() : null,
                usuario.getCreatedAt(),
                usuario.getUpdatedAt()
        );
    }

    public Usuario toEntity(UsuarioRequest request) {
        Usuario usuario = new Usuario();
        usuario.setNome(request.nome());
        usuario.setEmail(request.email());
        usuario.setSenha(request.senha());
        usuario.setCelular(request.celular());
        usuario.setRole(request.role());
        return usuario;
    }

    public void updateEntityFromRequest(UsuarioRequest request, Usuario usuario) {
        usuario.setNome(request.nome());
        usuario.setEmail(request.email());
        usuario.setSenha(request.senha());
        usuario.setCelular(request.celular());
        usuario.setRole(request.role());
    }
}