package com.ClassExchange.usecases.manter_disciplinas;

import com.ClassExchange.domain.entity.Curso;
import com.ClassExchange.domain.entity.Disciplina;
import com.ClassExchange.exception.NotFoundException;
import com.ClassExchange.usecases.manter_cursos.CursoRepository;
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
    private final DisciplinaMapper mapper;

    public DisciplinaResponse criar(DisciplinaRequest request) {
        Curso curso = cursoRepository.findById(request.cursoId())
                .orElseThrow(() -> new NotFoundException("Curso não encontrado"));

        Disciplina disciplina = Disciplina.builder()
                .nome(request.nome())
                .slug(SlugUtils.generateSlug(request.nome()))
                .cargaHoraria(request.cargaHoraria())
                .ementa(request.ementa())
                .curso(curso)
                .build();

        return toResponse(repository.save(disciplina));
    }

    public List<DisciplinaResponse> listarTodos() {
        return repository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    public Optional<DisciplinaResponse> buscarPorId(UUID id) {
        return repository.findById(id)
                .map(this::toResponse);
    }

    public DisciplinaResponse atualizar(UUID id, DisciplinaRequest request) {
        Disciplina disciplina = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Disciplina não encontrada"));

        Curso curso = cursoRepository.findById(request.cursoId())
                .orElseThrow(() -> new NotFoundException("Curso não encontrado"));

        disciplina.setNome(request.nome());
        disciplina.setSlug(SlugUtils.generateSlug(request.nome()));
        disciplina.setCargaHoraria(request.cargaHoraria());
        disciplina.setEmenta(request.ementa());
        disciplina.setCurso(curso);

        return toResponse(repository.save(disciplina));
    }

    public void deletar(UUID id) {
        if (!repository.existsById(id)) {
            throw new NotFoundException("Disciplina não encontrada");
        }
        repository.deleteById(id);
    }

    public Optional<DisciplinaResponse> buscarPorSlug(String slug) {
        return repository.findBySlug(slug)
                .map(this::toResponse);
    }

    private DisciplinaResponse toResponse(Disciplina disciplina) {
        return mapper.toResponse(disciplina);
    }
}
