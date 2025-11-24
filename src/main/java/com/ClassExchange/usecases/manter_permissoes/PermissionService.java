package com.ClassExchange.usecases.manter_permissoes;

import com.ClassExchange.domain.entity.Permission;
import com.ClassExchange.exception.BusinessException;
import com.ClassExchange.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PermissionService {

    private final PermissionRepository repository;
    private final PermissionMapper mapper;

    public PermissionResponse criar(PermissionRequest request) {
        repository.findByNome(request.nome()).ifPresent(r -> { throw new BusinessException("Permissão já existe"); });
        Permission p = mapper.toEntity(request);
        return mapper.toResponse(repository.save(p));
    }

    public List<PermissionResponse> listarTodos() {
        return repository.findAll().stream().map(mapper::toResponse).toList();
    }

    public Optional<PermissionResponse> buscarPorId(UUID id) {
        return repository.findById(id).map(mapper::toResponse);
    }

    public PermissionResponse atualizar(UUID id, PermissionRequest request) {
        Permission p = repository.findById(id).orElseThrow(() -> new NotFoundException("Permissão não encontrada"));
        mapper.updateEntityFromRequest(request, p);
        return mapper.toResponse(repository.save(p));
    }

    public void deletar(UUID id) {
        if (!repository.existsById(id)) {
            throw new NotFoundException("Permissão não encontrada");
        }
        repository.deleteById(id);
    }
}

