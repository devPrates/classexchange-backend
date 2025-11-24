package com.ClassExchange.usecases.manter_roles;

import com.ClassExchange.domain.entity.Role;
import com.ClassExchange.exception.BusinessException;
import com.ClassExchange.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository repository;
    private final RoleMapper mapper;

    public RoleResponse criar(RoleRequest request) {
        repository.findByNome(request.nome()).ifPresent(r -> { throw new BusinessException("Role já existe"); });
        Role role = mapper.toEntity(request);
        return mapper.toResponse(repository.save(role));
    }

    public List<RoleResponse> listarTodos() {
        return repository.findAll().stream().map(mapper::toResponse).toList();
    }

    public Optional<RoleResponse> buscarPorId(UUID id) {
        return repository.findById(id).map(mapper::toResponse);
    }

    public RoleResponse atualizar(UUID id, RoleRequest request) {
        Role role = repository.findById(id).orElseThrow(() -> new NotFoundException("Role não encontrada"));
        mapper.updateEntityFromRequest(request, role);
        return mapper.toResponse(repository.save(role));
    }

    public void deletar(UUID id) {
        if (!repository.existsById(id)) {
            throw new NotFoundException("Role não encontrada");
        }
        repository.deleteById(id);
    }
}

