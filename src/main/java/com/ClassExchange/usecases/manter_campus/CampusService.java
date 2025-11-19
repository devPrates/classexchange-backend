package com.ClassExchange.usecases.manter_campus;

import com.ClassExchange.domain.entity.Campus;
import com.ClassExchange.exception.NotFoundException;
import com.ClassExchange.usecases.manter_cursos.CursoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CampusService {

    private final CampusRepository repository;
    private final CursoRepository cursoRepository;
    private final CampusMapper mapper;

    public CampusResponse criar(@NonNull CampusRequest request) {
        Campus campus = mapper.toEntity(request);
        return toResponse(repository.save(campus));
    }

    public List<CampusResponse> listarTodos() {
        return repository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    public Optional<CampusResponse> buscarPorId(@NonNull UUID id) {
        return repository.findById(id)
                .map(this::toResponse);
    }

    public Optional<CampusResponse> buscarPorSlug(@NonNull String slug) {
        return repository.findBySlug(slug)
                .map(this::toResponse);
    }

    public CampusResponse atualizar(@NonNull UUID id, @NonNull CampusRequest request) {
        Campus campus = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Campus não encontrado"));

        mapper.updateEntityFromRequest(request, campus);
        return toResponse(repository.save(java.util.Objects.requireNonNull(campus)));
    }

    public void deletar(@NonNull UUID id) {
        if (!repository.existsById(id)) {
            throw new NotFoundException("Campus não encontrado");
        }
        repository.deleteById(id);
    }

    private CampusResponse toResponse(Campus campus) {
        List<CampusResponse.CursoSimplificado> cursos = cursoRepository.findByCampus(campus)
                .stream()
                .map(mapper::toCursoSimplificado)
                .toList();

        return mapper.toResponseWithCursos(campus, cursos);
    }

    
}
