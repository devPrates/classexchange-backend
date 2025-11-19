package com.ClassExchange.usecases.manter_disciplinaTurma;

import com.ClassExchange.domain.entity.Disciplina;
import com.ClassExchange.domain.entity.DisciplinaTurma;
import com.ClassExchange.domain.entity.Local;
import com.ClassExchange.domain.entity.Turma;
import com.ClassExchange.exception.NotFoundException;
import com.ClassExchange.usecases.manter_disciplinas.DisciplinaRepository;
import com.ClassExchange.usecases.manter_locais.LocalRepository;
import com.ClassExchange.usecases.manter_turmas.TurmaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DisciplinaTurmaService {

    private final DisciplinaTurmaRepository repository;
    private final DisciplinaRepository disciplinaRepository;
    private final TurmaRepository turmaRepository;
    private final LocalRepository localRepository;
    private final DisciplinaTurmaMapper mapper;

    public DisciplinaTurmaResponse criar(DisciplinaTurmaRequest request) {
        Disciplina disciplina = disciplinaRepository.findById(request.disciplinaId())
                .orElseThrow(() -> new NotFoundException("Disciplina não encontrada"));
        Turma turma = turmaRepository.findById(request.turmaId())
                .orElseThrow(() -> new NotFoundException("Turma não encontrada"));
        Local local = localRepository.findById(request.localId())
                .orElseThrow(() -> new NotFoundException("Local não encontrado"));

        DisciplinaTurma disciplinaTurma = mapper.toEntity(request, disciplina, turma, local);
        return toResponse(repository.save(disciplinaTurma));
    }

    public List<DisciplinaTurmaResponse> listarTodos() {
        return repository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    public Optional<DisciplinaTurmaResponse> buscarPorId(UUID id) {
        return repository.findById(id)
                .map(this::toResponse);
    }

    public DisciplinaTurmaResponse atualizar(UUID id, DisciplinaTurmaRequest request) {
        DisciplinaTurma disciplinaTurma = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("DisciplinaTurma não encontrada"));

        Disciplina disciplina = disciplinaRepository.findById(request.disciplinaId())
                .orElseThrow(() -> new NotFoundException("Disciplina não encontrada"));
        Turma turma = turmaRepository.findById(request.turmaId())
                .orElseThrow(() -> new NotFoundException("Turma não encontrada"));
        Local local = localRepository.findById(request.localId())
                .orElseThrow(() -> new NotFoundException("Local não encontrado"));

        mapper.updateEntityFromRequest(request, disciplinaTurma, disciplina, turma, local);
        return toResponse(repository.save(disciplinaTurma));
    }

    public void deletar(UUID id) {
        if (!repository.existsById(id)) {
            throw new NotFoundException("DisciplinaTurma não encontrada");
        }
        repository.deleteById(id);
    }

    private DisciplinaTurmaResponse toResponse(DisciplinaTurma disciplinaTurma) {
        return mapper.toResponse(disciplinaTurma);
    }
}