package com.ClassExchange.usecases.manter_locais;

import com.ClassExchange.domain.entity.Local;
import com.ClassExchange.domain.entity.Campus;
import com.ClassExchange.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LocalService {

    private final LocalRepository repository;
    private final LocalMapper mapper;
    private final com.ClassExchange.usecases.manter_campus.CampusRepository campusRepository;

    @Transactional
    public LocalResponse criar(LocalRequest request) {
        Campus campus = campusRepository.findById(request.campusId())
                .orElseThrow(() -> new NotFoundException("Campus não encontrado"));

        Local local = Local.builder()
                .nome(request.nome())
                .campus(campus)
                .capacidade(request.capacidade())
                .bloco(request.bloco())
                .andar(request.andar())
                .tipo(request.tipo())
                .build();

        return toResponse(repository.save(local));
    }

    public List<LocalResponse> listarTodos() {
        return repository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    public Optional<LocalResponse> buscarPorId(UUID id) {
        return repository.findById(id)
                .map(this::toResponse);
    }

    @Transactional
    public LocalResponse atualizar(UUID id, LocalRequest request) {
        Local local = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Local não encontrado"));

        Campus campus = campusRepository.findById(request.campusId())
                .orElseThrow(() -> new NotFoundException("Campus não encontrado"));

        local.setNome(request.nome());
        local.setCampus(campus);
        local.setCapacidade(request.capacidade());
        local.setBloco(request.bloco());
        local.setAndar(request.andar());
        local.setTipo(request.tipo());

        return toResponse(repository.save(local));
    }

    @Transactional
    public void deletar(UUID id) {
        if (!repository.existsById(id)) {
            throw new NotFoundException("Local não encontrado");
        }
        repository.deleteById(id);
    }

    private LocalResponse toResponse(Local local) {
        return mapper.toResponse(local);
    }
}
