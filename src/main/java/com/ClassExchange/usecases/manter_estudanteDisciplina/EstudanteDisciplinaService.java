package com.ClassExchange.usecases.manter_estudanteDisciplina;

import com.ClassExchange.domain.entity.DisciplinaTurma;
import com.ClassExchange.domain.entity.Estudante;
import com.ClassExchange.domain.entity.EstudanteDisciplina;
import com.ClassExchange.exception.NotFoundException;
import com.ClassExchange.usecases.manter_disciplinaTurma.DisciplinaTurmaRepository;
import com.ClassExchange.usecases.manter_estudantes.EstudanteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EstudanteDisciplinaService {

    private final EstudanteDisciplinaRepository repository;
    private final EstudanteRepository estudanteRepository;
    private final DisciplinaTurmaRepository disciplinaTurmaRepository;
    private final EstudanteDisciplinaMapper mapper;

    public EstudanteDisciplinaResponse criar(EstudanteDisciplinaRequest request) {
        Estudante estudante = estudanteRepository.findById(request.estudanteId())
                .orElseThrow(() -> new NotFoundException("Estudante não encontrado"));
        DisciplinaTurma disciplinaTurma = disciplinaTurmaRepository.findById(request.disciplinaTurmaId())
                .orElseThrow(() -> new NotFoundException("DisciplinaTurma não encontrada"));

        EstudanteDisciplina ed = mapper.toEntity(request, estudante, disciplinaTurma);
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
        Estudante estudante = estudanteRepository.findById(request.estudanteId())
                .orElseThrow(() -> new NotFoundException("Estudante não encontrado"));
        DisciplinaTurma disciplinaTurma = disciplinaTurmaRepository.findById(request.disciplinaTurmaId())
                .orElseThrow(() -> new NotFoundException("DisciplinaTurma não encontrada"));

        mapper.updateEntityFromRequest(request, ed, estudante, disciplinaTurma);
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