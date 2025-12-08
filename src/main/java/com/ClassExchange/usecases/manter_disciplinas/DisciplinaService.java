package com.ClassExchange.usecases.manter_disciplinas;

import com.ClassExchange.domain.entity.Curso;
import com.ClassExchange.domain.entity.Disciplina;
import com.ClassExchange.domain.entity.Periodo;
import com.ClassExchange.exception.NotFoundException;
import com.ClassExchange.usecases.manter_cursos.CursoRepository;
import com.ClassExchange.usecases.manter_campus.CampusRepository;
import com.ClassExchange.usecases.manter_periodos.PeriodoRepository;
import com.ClassExchange.utils.SlugUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DisciplinaService {

    private final DisciplinaRepository repository;
    private final CursoRepository cursoRepository;
    private final CampusRepository campusRepository;
    private final PeriodoRepository periodoRepository;
    private final DisciplinaMapper mapper;

    public DisciplinaResponse criar(DisciplinaRequest request) {
        Curso curso = cursoRepository.findById(request.cursoId())
                .orElseThrow(() -> new NotFoundException("Curso não encontrado"));
        Periodo periodo = periodoRepository.findById(request.periodoId())
                .orElseThrow(() -> new NotFoundException("Período não encontrado"));
        Disciplina disciplina = mapper.toEntity(request, curso, periodo);
        disciplina.setSlug(SlugUtils.generateSlug(request.nome()));

        return toResponse(repository.save(disciplina));
    }

    public List<DisciplinaResponse> listarTodos() {
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
        return cursos.stream()
                .flatMap(c -> repository.findByCurso(c).stream())
                .map(this::toResponse)
                .toList();
    }

    public Optional<DisciplinaResponse> buscarPorId(UUID id) {
        Optional<Disciplina> opt = repository.findById(id);
        if (opt.isEmpty()) return java.util.Optional.empty();
        Disciplina disciplina = opt.get();
        if (!com.ClassExchange.security.SecurityUtils.isAdmin()) {
            String campusId = com.ClassExchange.security.SecurityUtils.currentCampusId();
            com.ClassExchange.domain.entity.Curso curso = disciplina.getCurso();
            if (campusId == null || curso == null || curso.getCampus() == null || !curso.getCampus().getId().toString().equals(campusId)) {
                return java.util.Optional.empty();
            }
        }
        return java.util.Optional.of(this.toResponse(disciplina));
    }

    public DisciplinaResponse atualizar(UUID id, DisciplinaRequest request) {
        Disciplina disciplina = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Disciplina não encontrada"));

        Curso curso = cursoRepository.findById(request.cursoId())
                .orElseThrow(() -> new NotFoundException("Curso não encontrado"));
        Periodo periodo = periodoRepository.findById(request.periodoId())
                .orElseThrow(() -> new NotFoundException("Período não encontrado"));
        mapper.updateEntityFromRequest(request, disciplina, curso, periodo);
        disciplina.setSlug(SlugUtils.generateSlug(request.nome()));

        return toResponse(repository.save(disciplina));
    }

    public void deletar(UUID id) {
        if (!repository.existsById(id)) {
            throw new NotFoundException("Disciplina não encontrada");
        }
        repository.deleteById(id);
    }

    public Optional<DisciplinaResponse> buscarPorSlug(String slug) {
        Optional<Disciplina> opt = repository.findBySlug(slug);
        if (opt.isEmpty()) return java.util.Optional.empty();
        Disciplina disciplina = opt.get();
        if (!com.ClassExchange.security.SecurityUtils.isAdmin()) {
            String campusId = com.ClassExchange.security.SecurityUtils.currentCampusId();
            com.ClassExchange.domain.entity.Curso curso = disciplina.getCurso();
            if (campusId == null || curso == null || curso.getCampus() == null || !curso.getCampus().getId().toString().equals(campusId)) {
                return java.util.Optional.empty();
            }
        }
        return java.util.Optional.of(this.toResponse(disciplina));
    }

    private DisciplinaResponse toResponse(Disciplina disciplina) {
        return mapper.toResponse(disciplina);
    }
}
