package com.ClassExchange.usecases.manter_estudanteCurso;

import com.ClassExchange.domain.entity.Curso;
import com.ClassExchange.domain.entity.Estudante;
import com.ClassExchange.domain.entity.EstudanteCurso;
import com.ClassExchange.domain.enums.SituacaoClasse;
import com.ClassExchange.exception.NotFoundException;
import com.ClassExchange.usecases.manter_cursos.CursoRepository;
import com.ClassExchange.usecases.manter_estudantes.EstudanteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class EstudanteCursoService {

    private final EstudanteCursoRepository repository;
    private final EstudanteRepository estudanteRepository;
    private final CursoRepository cursoRepository;
    private final EstudanteCursoMapper mapper;

    public EstudanteCursoResponse criar(EstudanteCursoRequest request) {
        Estudante estudante = estudanteRepository.findById(request.estudanteId())
                .orElseThrow(() -> new NotFoundException("Estudante não encontrado"));
        Curso curso = cursoRepository.findById(request.cursoId())
                .orElseThrow(() -> new NotFoundException("Curso não encontrado"));

        LocalDate vinculo = request.vinculoCurso() != null ? request.vinculoCurso() : LocalDate.now();
        SituacaoClasse situacao = request.situacao() != null ? request.situacao() : SituacaoClasse.EM_CURSO;

        String matricula = generateMatricula(curso, vinculo);

        EstudanteCurso ec = EstudanteCurso.builder()
                .matricula(matricula)
                .vinculoCurso(vinculo)
                .situacao(situacao)
                .estudante(estudante)
                .curso(curso)
                .build();

        return mapper.toResponse(repository.save(ec));
    }

    private String generateMatricula(Curso curso, LocalDate vinculo) {
        String sigla = curso.getSigla();
        int ano = vinculo != null ? vinculo.getYear() : LocalDate.now().getYear();
        long seq = repository.countByCursoId(curso.getId()) + 1;
        return String.format("%s%d-%04d", sigla, ano, seq);
    }

    public java.util.List<EstudanteCursoResponse> listarTodos() {
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .toList();
    }

    public java.util.Optional<EstudanteCursoResponse> buscarPorId(java.util.UUID id) {
        return repository.findById(id)
                .map(mapper::toResponse);
    }

    public EstudanteCursoResponse atualizar(java.util.UUID id, EstudanteCursoRequest request) {
        EstudanteCurso ec = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("EstudanteCurso não encontrado"));

        Estudante estudante = estudanteRepository.findById(request.estudanteId())
                .orElseThrow(() -> new NotFoundException("Estudante não encontrado"));
        Curso curso = cursoRepository.findById(request.cursoId())
                .orElseThrow(() -> new NotFoundException("Curso não encontrado"));

        LocalDate vinculo = request.vinculoCurso() != null ? request.vinculoCurso() : ec.getVinculoCurso();
        com.ClassExchange.domain.enums.SituacaoClasse situacao = request.situacao() != null ? request.situacao() : ec.getSituacao();

        String matricula = generateMatricula(curso, vinculo);

        ec.setEstudante(estudante);
        ec.setCurso(curso);
        ec.setVinculoCurso(vinculo);
        ec.setSituacao(situacao);
        ec.setMatricula(matricula);

        return mapper.toResponse(repository.save(ec));
    }

    public void deletar(java.util.UUID id) {
        if (!repository.existsById(id)) {
            throw new NotFoundException("EstudanteCurso não encontrado");
        }
        repository.deleteById(id);
    }
}