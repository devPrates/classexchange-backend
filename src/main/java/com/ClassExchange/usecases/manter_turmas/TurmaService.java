package com.ClassExchange.usecases.manter_turmas;

import com.ClassExchange.domain.entity.Curso;
import com.ClassExchange.domain.entity.Turma;
import com.ClassExchange.exception.NotFoundException;
import com.ClassExchange.usecases.manter_cursos.CursoRepository;
import com.ClassExchange.usecases.manter_campus.CampusRepository;
import com.ClassExchange.usecases.manter_periodos.PeriodoRepository;
import com.ClassExchange.usecases.manter_periodos.PeriodoMapper;
import com.ClassExchange.usecases.manter_periodos.PeriodoResponse;
import com.ClassExchange.utils.SlugUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TurmaService {

    private final TurmaRepository repository;
    private final CursoRepository cursoRepository;
    private final CampusRepository campusRepository;
    private final TurmaMapper mapper;
    private final PeriodoRepository periodoRepository;
    private final PeriodoMapper periodoMapper;

    public TurmaResponse criar(TurmaRequest request) {
        Curso curso = cursoRepository.findById(request.cursoId())
                .orElseThrow(() -> new NotFoundException("Curso não encontrado"));

        Turma turma = mapper.toEntity(request, curso);
        turma.setSlug(SlugUtils.generateSlug(request.nome()));

        return toResponse(repository.save(turma));
    }

    public List<TurmaResponse> listarTodos() {
        if (com.ClassExchange.security.SecurityUtils.isAdmin()) {
            return repository.findAll().stream().map(this::toResponse).toList();
        }
        String campusId = com.ClassExchange.security.SecurityUtils.currentCampusId();
        if (campusId == null) {
            return java.util.List.of();
        }
        java.util.UUID id = java.util.UUID.fromString(campusId);
        com.ClassExchange.domain.entity.Campus campus = campusRepository.findById(id).orElse(null);
        java.util.List<com.ClassExchange.domain.entity.Curso> cursos = cursoRepository.findByCampus(campus);
        return cursos.stream().flatMap(c -> repository.findByCurso(c).stream()).map(this::toResponse).toList();
    }

    public Optional<TurmaResponse> buscarPorId(UUID id) {
        Optional<Turma> opt = repository.findById(id);
        if (opt.isEmpty()) return java.util.Optional.empty();
        Turma turma = opt.get();
        if (!com.ClassExchange.security.SecurityUtils.isAdmin()) {
            String campusId = com.ClassExchange.security.SecurityUtils.currentCampusId();
            com.ClassExchange.domain.entity.Curso curso = turma.getCurso();
            if (campusId == null || curso == null || curso.getCampus() == null || !curso.getCampus().getId().toString().equals(campusId)) {
                return java.util.Optional.empty();
            }
        }
        return java.util.Optional.of(this.toResponse(turma));
    }

    public TurmaResponse atualizar(UUID id, TurmaRequest request) {
        Turma turma = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Turma não encontrada"));

        Curso curso = cursoRepository.findById(request.cursoId())
                .orElseThrow(() -> new NotFoundException("Curso não encontrado"));

        mapper.updateEntityFromRequest(request, turma, curso);
        turma.setSlug(SlugUtils.generateSlug(request.nome()));

        return toResponse(repository.save(turma));
    }

    public void deletar(UUID id) {
        if (!repository.existsById(id)) {
            throw new NotFoundException("Turma não encontrada");
        }
        repository.deleteById(id);
    }

    public Optional<TurmaResponse> buscarPorSlug(String slug) {
        Optional<Turma> opt = repository.findBySlug(slug);
        if (opt.isEmpty()) return java.util.Optional.empty();
        Turma turma = opt.get();
        if (!com.ClassExchange.security.SecurityUtils.isAdmin()) {
            String campusId = com.ClassExchange.security.SecurityUtils.currentCampusId();
            com.ClassExchange.domain.entity.Curso curso = turma.getCurso();
            if (campusId == null || curso == null || curso.getCampus() == null || !curso.getCampus().getId().toString().equals(campusId)) {
                return java.util.Optional.empty();
            }
        }
        return java.util.Optional.of(this.toResponse(turma));
    }

    private TurmaResponse toResponse(Turma turma) {
        return mapper.toResponse(turma);
    }

    public java.util.Optional<TurmaComPeriodosResponse> buscarPorSlugComPeriodos(String slug) {
        return repository.findBySlug(slug)
                .map(t -> {
                    if (!com.ClassExchange.security.SecurityUtils.isAdmin()) {
                        String campusId = com.ClassExchange.security.SecurityUtils.currentCampusId();
                        com.ClassExchange.domain.entity.Curso curso = t.getCurso();
                        if (campusId == null || curso == null || curso.getCampus() == null || !curso.getCampus().getId().toString().equals(campusId)) {
                            return null;
                        }
                    }
                    java.util.List<PeriodoResponse> periodos = periodoRepository.findByTurmaId(t.getId()).stream()
                            .map(periodoMapper::toResponse)
                            .toList();
                    return mapper.toResponseComPeriodos(t, periodos);
                });
    }

    public java.util.List<TurmaResponse> listarPorCursoId(java.util.UUID cursoId) {
        java.util.Optional<com.ClassExchange.domain.entity.Curso> opt = cursoRepository.findById(cursoId);
        if (opt.isEmpty()) return java.util.List.of();
        com.ClassExchange.domain.entity.Curso curso = opt.get();
        if (!com.ClassExchange.security.SecurityUtils.isAdmin()) {
            String campusId = com.ClassExchange.security.SecurityUtils.currentCampusId();
            if (campusId == null || curso.getCampus() == null || !curso.getCampus().getId().toString().equals(campusId)) {
                return java.util.List.of();
            }
        }
        return repository.findByCurso(curso).stream().map(this::toResponse).toList();
    }

    public java.util.List<TurmaResponse> listarPorCursoSlug(String slug) {
        com.ClassExchange.domain.entity.Curso curso = cursoRepository.findBySlug(slug).orElse(null);
        if (curso == null) return java.util.List.of();
        if (!com.ClassExchange.security.SecurityUtils.isAdmin()) {
            String campusId = com.ClassExchange.security.SecurityUtils.currentCampusId();
            if (campusId == null || curso.getCampus() == null || !curso.getCampus().getId().toString().equals(campusId)) {
                return java.util.List.of();
            }
        }
        return repository.findByCurso(curso).stream().map(this::toResponse).toList();
    }
}
