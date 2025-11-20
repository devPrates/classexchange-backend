package com.ClassExchange.usecases.manter_usuarios;

import com.ClassExchange.domain.entity.Usuario;
import com.ClassExchange.domain.entity.Campus;
import com.ClassExchange.usecases.manter_campus.CampusRepository;
import com.ClassExchange.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository repository;
    private final CampusRepository campusRepository;
    private final UsuarioMapper mapper;

    public UsuarioResponse criar(UsuarioRequest request) {
        if (request.senha() == null || request.senha().isBlank()) {
            throw new com.ClassExchange.exception.BusinessException("Senha é obrigatória na criação de usuário");
        }
        Usuario usuario = mapper.toEntity(request);
        Campus campus = campusRepository.findById(request.campusId())
                .orElseThrow(() -> new NotFoundException("Campus não encontrado"));
        usuario.setCampus(campus);
        return toResponse(repository.save(usuario));
    }

    public List<UsuarioResponse> listarTodos() {
        return repository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    public Optional<UsuarioResponse> buscarPorId(UUID id) {
        return repository.findById(id)
                .map(this::toResponse);
    }

    public UsuarioResponse atualizar(UUID id, UsuarioRequest request) {
        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado"));

        mapper.updateEntityFromRequest(request, usuario);
        Campus campus = campusRepository.findById(request.campusId())
                .orElseThrow(() -> new NotFoundException("Campus não encontrado"));
        usuario.setCampus(campus);
        return toResponse(repository.save(usuario));
    }

    public void deletar(UUID id) {
        if (!repository.existsById(id)) {
            throw new NotFoundException("Usuário não encontrado");
        }
        repository.deleteById(id);
    }

    private UsuarioResponse toResponse(Usuario usuario) {
        return mapper.toResponse(usuario);
    }
}