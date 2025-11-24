package com.ClassExchange.usecases.manter_usuarioRole;

import com.ClassExchange.domain.entity.Role;
import com.ClassExchange.domain.entity.Usuario;
import com.ClassExchange.domain.entity.UsuarioRole;
import com.ClassExchange.exception.BusinessException;
import com.ClassExchange.exception.NotFoundException;
import com.ClassExchange.usecases.manter_roles.RoleRepository;
import com.ClassExchange.usecases.manter_usuarios.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UsuarioRoleService {

    private final UsuarioRoleRepository repository;
    private final UsuarioRepository usuarioRepository;
    private final RoleRepository roleRepository;
    private final UsuarioRoleMapper mapper;

    public UsuarioRoleResponse criar(UsuarioRoleRequest request) {
        Usuario usuario = usuarioRepository.findById(request.usuarioId()).orElseThrow(() -> new NotFoundException("Usuário não encontrado"));
        Role role = roleRepository.findById(request.roleId()).orElseThrow(() -> new NotFoundException("Role não encontrada"));
        repository.findByUsuarioAndRole(usuario, role).ifPresent(r -> { throw new BusinessException("Vínculo já existe"); });
        UsuarioRole ur = mapper.toEntity(request, usuario, role);
        return mapper.toResponse(repository.save(ur));
    }

    public List<UsuarioRoleResponse> listarTodos() {
        return repository.findAll().stream().map(mapper::toResponse).toList();
    }

    public Optional<UsuarioRoleResponse> buscarPorId(UUID id) {
        return repository.findById(id).map(mapper::toResponse);
    }

    public UsuarioRoleResponse atualizar(UUID id, UsuarioRoleRequest request) {
        UsuarioRole ur = repository.findById(id).orElseThrow(() -> new NotFoundException("Vínculo não encontrado"));
        Usuario usuario = usuarioRepository.findById(request.usuarioId()).orElseThrow(() -> new NotFoundException("Usuário não encontrado"));
        Role role = roleRepository.findById(request.roleId()).orElseThrow(() -> new NotFoundException("Role não encontrada"));
        mapper.updateEntityFromRequest(request, ur, usuario, role);
        return mapper.toResponse(repository.save(ur));
    }

    public void deletar(UUID id) {
        if (!repository.existsById(id)) {
            throw new NotFoundException("Vínculo não encontrado");
        }
        repository.deleteById(id);
    }
}

