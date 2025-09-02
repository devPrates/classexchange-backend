package com.ClassExchange.usecases.manter_campus;

import com.ClassExchange.domain.entity.Campus;
import com.ClassExchange.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CampusService {

    private final CampusRepository repository;

    public CampusResponse criar(CampusRequest request) {
        Campus campus = Campus.builder()
                .nome(request.nome())
                .sigla(request.sigla())
                .email(request.email())
                .build();

        return toResponse(repository.save(campus));
    }

    public List<CampusResponse> listarTodos() {
        return repository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    public Optional<CampusResponse> buscarPorId(UUID id) {
        return repository.findById(id)
                .map(this::toResponse);
    }

    public CampusResponse atualizar(UUID id, CampusRequest request) {
        Campus campus = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Campus não encontrado"));

        campus.setNome(request.nome());
        campus.setSigla(request.sigla());
        campus.setEmail(request.email());

        return toResponse(repository.save(campus));
    }

    public void deletar(UUID id) {
        if (!repository.existsById(id)) {
            throw new NotFoundException("Campus não encontrado");
        }
        repository.deleteById(id);
    }

    private CampusResponse toResponse(Campus campus) {
        return new CampusResponse(
                campus.getId(),
                campus.getNome(),
                campus.getSigla(),
                campus.getEmail(),
                campus.getCreatedAt(),
                campus.getUpdatedAt()
        );
    }
}
