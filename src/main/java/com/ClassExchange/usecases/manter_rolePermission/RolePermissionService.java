package com.ClassExchange.usecases.manter_rolePermission;

import com.ClassExchange.domain.entity.Permission;
import com.ClassExchange.domain.entity.Role;
import com.ClassExchange.domain.entity.RolePermission;
import com.ClassExchange.exception.BusinessException;
import com.ClassExchange.exception.NotFoundException;
import com.ClassExchange.usecases.manter_permissoes.PermissionRepository;
import com.ClassExchange.usecases.manter_roles.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RolePermissionService {

    private final RolePermissionRepository repository;
    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;
    private final RolePermissionMapper mapper;

    public RolePermissionResponse criar(RolePermissionRequest request) {
        Role role = roleRepository.findById(request.roleId()).orElseThrow(() -> new NotFoundException("Role não encontrada"));
        Permission permission = permissionRepository.findById(request.permissionId()).orElseThrow(() -> new NotFoundException("Permissão não encontrada"));
        repository.findByRoleAndPermission(role, permission).ifPresent(r -> { throw new BusinessException("Vínculo já existe"); });
        RolePermission rp = mapper.toEntity(request, role, permission);
        return mapper.toResponse(repository.save(rp));
    }

    public List<RolePermissionResponse> listarTodos() {
        return repository.findAll().stream().map(mapper::toResponse).toList();
    }

    public Optional<RolePermissionResponse> buscarPorId(UUID id) {
        return repository.findById(id).map(mapper::toResponse);
    }

    public RolePermissionResponse atualizar(UUID id, RolePermissionRequest request) {
        RolePermission rp = repository.findById(id).orElseThrow(() -> new NotFoundException("Vínculo não encontrado"));
        Role role = roleRepository.findById(request.roleId()).orElseThrow(() -> new NotFoundException("Role não encontrada"));
        Permission permission = permissionRepository.findById(request.permissionId()).orElseThrow(() -> new NotFoundException("Permissão não encontrada"));
        mapper.updateEntityFromRequest(request, rp, role, permission);
        return mapper.toResponse(repository.save(rp));
    }

    public void deletar(UUID id) {
        if (!repository.existsById(id)) {
            throw new NotFoundException("Vínculo não encontrado");
        }
        repository.deleteById(id);
    }
}

