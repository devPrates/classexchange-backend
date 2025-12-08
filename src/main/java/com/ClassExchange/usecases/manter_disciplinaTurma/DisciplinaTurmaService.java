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
        if (com.ClassExchange.security.SecurityUtils.isAdmin()) {
            return repository.findAll().stream().map(this::toResponse).toList();
        }
        String campusId = com.ClassExchange.security.SecurityUtils.currentCampusId();
        if (campusId == null) {
            return java.util.List.of();
        }
        return repository.findAll().stream()
                .filter(dt -> dt.getTurma() != null && dt.getTurma().getCurso() != null && dt.getTurma().getCurso().getCampus() != null && campusId.equals(dt.getTurma().getCurso().getCampus().getId().toString()))
                .map(this::toResponse)
                .toList();
    }

    public Optional<DisciplinaTurmaResponse> buscarPorId(UUID id) {
        java.util.Optional<DisciplinaTurma> opt = repository.findById(id);
        if (opt.isEmpty()) return java.util.Optional.empty();
        DisciplinaTurma dt = opt.get();
        if (!com.ClassExchange.security.SecurityUtils.isAdmin()) {
            String campusId = com.ClassExchange.security.SecurityUtils.currentCampusId();
            if (campusId == null || dt.getTurma() == null || dt.getTurma().getCurso() == null || dt.getTurma().getCurso().getCampus() == null || !campusId.equals(dt.getTurma().getCurso().getCampus().getId().toString())) {
                return java.util.Optional.empty();
            }
        }
        return java.util.Optional.of(this.toResponse(dt));
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
