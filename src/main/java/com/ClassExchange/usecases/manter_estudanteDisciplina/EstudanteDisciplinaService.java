package com.ClassExchange.usecases.manter_estudanteDisciplina;

import com.ClassExchange.domain.entity.EstudanteCurso;
import com.ClassExchange.domain.entity.EstudanteDisciplina;
import com.ClassExchange.domain.entity.Aula;
import com.ClassExchange.exception.BusinessException;
import com.ClassExchange.exception.NotFoundException;
import com.ClassExchange.usecases.manter_estudanteCurso.EstudanteCursoRepository;
import com.ClassExchange.usecases.manter_aulas.AulaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EstudanteDisciplinaService {

    private final EstudanteDisciplinaRepository repository;
    private final EstudanteCursoRepository estudanteCursoRepository;
    private final AulaRepository aulaRepository;
    private final EstudanteDisciplinaMapper mapper;

    public EstudanteDisciplinaResponse criar(EstudanteDisciplinaRequest request) {
        EstudanteCurso estudanteCurso = estudanteCursoRepository.findById(request.estudanteCursoId())
                .orElseThrow(() -> new NotFoundException("EstudanteCurso não encontrado"));
        Aula aula = aulaRepository.findById(request.aulaId())
                .orElseThrow(() -> new NotFoundException("Aula não encontrada"));

        repository.findByEstudanteCursoAndAula(estudanteCurso, aula)
                .ifPresent(x -> { throw new BusinessException("Vínculo já existe para este estudante/curso e aula"); });

        EstudanteDisciplina ed = mapper.toEntity(request, estudanteCurso, aula);
        return toResponse(repository.save(ed));
    }

    public List<EstudanteDisciplinaResponse> listarTodos() {
        return repository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    public Optional<EstudanteDisciplinaResponse> buscarPorId(UUID id) {
        return repository.findById(id)
                .map(this::toResponse);
    }

    public EstudanteDisciplinaResponse atualizar(UUID id, EstudanteDisciplinaRequest request) {
        EstudanteDisciplina ed = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("EstudanteDisciplina não encontrada"));
        EstudanteCurso estudanteCurso = estudanteCursoRepository.findById(request.estudanteCursoId())
                .orElseThrow(() -> new NotFoundException("EstudanteCurso não encontrado"));
        Aula aula = aulaRepository.findById(request.aulaId())
                .orElseThrow(() -> new NotFoundException("Aula não encontrada"));

        mapper.updateEntityFromRequest(request, ed, estudanteCurso, aula);
        return toResponse(repository.save(ed));
    }

    public void deletar(UUID id) {
        if (!repository.existsById(id)) {
            throw new NotFoundException("EstudanteDisciplina não encontrada");
        }
        repository.deleteById(id);
    }

    private EstudanteDisciplinaResponse toResponse(EstudanteDisciplina ed) {
        return mapper.toResponse(ed);
    }
}
