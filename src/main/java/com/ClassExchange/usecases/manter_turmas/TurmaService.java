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
@org.springframework.transaction.annotation.Transactional(readOnly = true)
public class TurmaService {

    private final TurmaRepository repository;
    private final CursoRepository cursoRepository;
    private final CampusRepository campusRepository;
    private final TurmaMapper mapper;
    private final PeriodoRepository periodoRepository;
    private final PeriodoMapper periodoMapper;

    @org.springframework.transaction.annotation.Transactional
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
            return List.of();
        }
        UUID id = UUID.fromString(campusId);
        com.ClassExchange.domain.entity.Campus campus = campusRepository.findById(id).orElse(null);
        List<com.ClassExchange.domain.entity.Curso> cursos = cursoRepository.findByCampus(campus);
        return cursos.stream().flatMap(c -> repository.findByCurso(c).stream()).map(this::toResponse).toList();
    }

    public Optional<TurmaResponse> buscarPorId(UUID id) {
        return repository.findById(id)
                .filter(turma -> {
                    if (com.ClassExchange.security.SecurityUtils.isAdmin()) return true;
                    String campusId = com.ClassExchange.security.SecurityUtils.currentCampusId();
                    Curso curso = turma.getCurso();
                    return campusId != null && curso != null && curso.getCampus() != null && curso.getCampus().getId().toString().equals(campusId);
                })
                .map(this::toResponse);
    }

    @org.springframework.transaction.annotation.Transactional
    public TurmaResponse atualizar(UUID id, TurmaRequest request) {
        Turma turma = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Turma não encontrada"));

        Curso curso = cursoRepository.findById(request.cursoId())
                .orElseThrow(() -> new NotFoundException("Curso não encontrado"));

        mapper.updateEntityFromRequest(request, turma, curso);
        turma.setSlug(SlugUtils.generateSlug(request.nome()));

        return toResponse(repository.save(turma));
    }

    @org.springframework.transaction.annotation.Transactional
    public void deletar(UUID id) {
        if (!repository.existsById(id)) {
            throw new NotFoundException("Turma não encontrada");
        }
        repository.deleteById(id);
    }

    public Optional<TurmaResponse> buscarPorSlug(String slug) {
        return repository.findBySlug(slug)
                .filter(turma -> {
                    if (com.ClassExchange.security.SecurityUtils.isAdmin()) return true;
                    String campusId = com.ClassExchange.security.SecurityUtils.currentCampusId();
                    Curso curso = turma.getCurso();
                    return campusId != null && curso != null && curso.getCampus() != null && curso.getCampus().getId().toString().equals(campusId);
                })
                .map(this::toResponse);
    }

    private TurmaResponse toResponse(Turma turma) {
        return mapper.toResponse(turma);
    }

    public Optional<TurmaComPeriodosResponse> buscarPorSlugComPeriodos(String slug) {
        return repository.findBySlug(slug)
                .filter(turma -> {
                    if (com.ClassExchange.security.SecurityUtils.isAdmin()) return true;
                    String campusId = com.ClassExchange.security.SecurityUtils.currentCampusId();
                    Curso curso = turma.getCurso();
                    return campusId != null && curso != null && curso.getCampus() != null && curso.getCampus().getId().toString().equals(campusId);
                })
                .map(t -> {
                    List<PeriodoResponse> periodos = periodoRepository.findByTurmaId(t.getId()).stream()
                            .map(periodoMapper::toResponse)
                            .toList();
                    return mapper.toResponseComPeriodos(t, periodos);
                });
    }

    public List<TurmaResponse> listarPorCursoId(UUID cursoId) {
        return cursoRepository.findById(cursoId)
                .filter(curso -> {
                    if (com.ClassExchange.security.SecurityUtils.isAdmin()) return true;
                    String campusId = com.ClassExchange.security.SecurityUtils.currentCampusId();
                    return campusId != null && curso.getCampus() != null && curso.getCampus().getId().toString().equals(campusId);
                })
                .map(curso -> repository.findByCurso(curso).stream().map(this::toResponse).toList())
                .orElse(List.of());
    }

    public List<TurmaResponse> listarPorCursoSlug(String slug) {
        return cursoRepository.findBySlug(slug)
                .filter(curso -> {
                    if (com.ClassExchange.security.SecurityUtils.isAdmin()) return true;
                    String campusId = com.ClassExchange.security.SecurityUtils.currentCampusId();
                    return campusId != null && curso.getCampus() != null && curso.getCampus().getId().toString().equals(campusId);
                })
                .map(curso -> repository.findByCurso(curso).stream().map(this::toResponse).toList())
                .orElse(List.of());
    }
}
