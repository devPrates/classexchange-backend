package com.ClassExchange.usecases.manter_turmas;

import com.ClassExchange.domain.entity.Curso;
import com.ClassExchange.domain.entity.Turma;
import com.ClassExchange.exception.NotFoundException;
import com.ClassExchange.usecases.manter_cursos.CursoRepository;
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

    public TurmaResponse criar(TurmaRequest request) {
        Curso curso = cursoRepository.findById(request.cursoId())
                .orElseThrow(() -> new NotFoundException("Curso não encontrado"));

        Turma turma = Turma.builder()
                .nome(request.nome())
                .numero(request.numero())
                .curso(curso)
                .build();

        return toResponse(repository.save(turma));
    }

    public List<TurmaResponse> listarTodos() {
        return repository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    public Optional<TurmaResponse> buscarPorId(UUID id) {
        return repository.findById(id)
                .map(this::toResponse);
    }

    public TurmaResponse atualizar(UUID id, TurmaRequest request) {
        Turma turma = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Turma não encontrada"));

        Curso curso = cursoRepository.findById(request.cursoId())
                .orElseThrow(() -> new NotFoundException("Curso não encontrado"));

        turma.setNome(request.nome());
        turma.setNumero(request.numero());
        turma.setCurso(curso);

        return toResponse(repository.save(turma));
    }

    public void deletar(UUID id) {
        if (!repository.existsById(id)) {
            throw new NotFoundException("Turma não encontrada");
        }
        repository.deleteById(id);
    }

    private TurmaResponse toResponse(Turma turma) {
        return new TurmaResponse(
                turma.getId(),
                turma.getNome(),
                turma.getNumero(),
                turma.getCurso().getId(),
                turma.getCurso().getNome(),
                turma.getCreatedAt(),
                turma.getUpdatedAt()
        );
    }
}