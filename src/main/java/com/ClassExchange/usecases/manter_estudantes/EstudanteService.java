package com.ClassExchange.usecases.manter_estudantes;

import com.ClassExchange.domain.entity.Estudante;
import com.ClassExchange.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EstudanteService {

    private final EstudanteRepository repository;
    private final EstudanteMapper mapper;

    @Transactional
    public EstudanteResponse criar(EstudanteRequest request) {
        Estudante estudante = Estudante.builder()
                .nome(request.nome())
                .email(request.email())
                .build();

        return toResponse(repository.save(estudante));
    }

    @Transactional(readOnly = true)
    public List<EstudanteResponse> listarTodos() {
        return repository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public Optional<EstudanteResponse> buscarPorId(UUID id) {
        return repository.findById(id)
                .map(this::toResponse);
    }

    @Transactional
    public EstudanteResponse atualizar(UUID id, EstudanteRequest request) {
        Estudante estudante = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Estudante não encontrado"));

        estudante.setNome(request.nome());
        estudante.setEmail(request.email());

        return toResponse(repository.save(estudante));
    }

    @Transactional
    public void deletar(UUID id) {
        if (!repository.existsById(id)) {
            throw new NotFoundException("Estudante não encontrado");
        }
        repository.deleteById(id);
    }

    private EstudanteResponse toResponse(Estudante estudante) {
        return mapper.toResponse(estudante);
    }
}
