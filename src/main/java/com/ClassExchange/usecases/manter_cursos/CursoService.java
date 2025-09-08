package com.ClassExchange.usecases.manter_cursos;

import com.ClassExchange.domain.entity.Campus;
import com.ClassExchange.domain.entity.Curso;
import com.ClassExchange.exception.NotFoundException;
import com.ClassExchange.usecases.manter_campus.CampusRepository;
import com.ClassExchange.usecases.manter_disciplinas.DisciplinaRepository;
import com.ClassExchange.usecases.manter_turmas.TurmaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CursoService {

    private final CursoRepository repository;
    private final CampusRepository campusRepository;
    private final DisciplinaRepository disciplinaRepository;
    private final TurmaRepository turmaRepository;
    private final CursoMapper mapper;

    public CursoResponse criar(CursoRequest request) {
        Campus campus = campusRepository.findById(request.campusId())
                .orElseThrow(() -> new NotFoundException("Campus não encontrado"));

        Curso curso = Curso.builder()
                .nome(request.nome())
                .sigla(request.sigla())
                .campus(campus)
                .build();

        return toResponse(repository.save(curso));
    }

    public List<CursoResponse> listarTodos() {
        return repository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    public Optional<CursoResponse> buscarPorId(UUID id) {
        return repository.findById(id)
                .map(this::toResponse);
    }

    public CursoResponse atualizar(UUID id, CursoRequest request) {
        Curso curso = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Curso não encontrado"));

        Campus campus = campusRepository.findById(request.campusId())
                .orElseThrow(() -> new NotFoundException("Campus não encontrado"));

        curso.setNome(request.nome());
        curso.setSigla(request.sigla());
        curso.setCampus(campus);

        return toResponse(repository.save(curso));
    }

    public void deletar(UUID id) {
        if (!repository.existsById(id)) {
            throw new NotFoundException("Curso não encontrado");
        }
        repository.deleteById(id);
    }

    private CursoResponse toResponse(Curso curso) {
        List<CursoResponse.DisciplinaSimplificada> disciplinas = disciplinaRepository.findByCurso(curso)
                .stream()
                .map(mapper::toDisciplinaSimplificada)
                .toList();

        List<CursoResponse.TurmaSimplificada> turmas = turmaRepository.findByCurso(curso)
                .stream()
                .map(mapper::toTurmaSimplificada)
                .toList();

        return mapper.toResponse(curso, disciplinas, turmas);
    }
}