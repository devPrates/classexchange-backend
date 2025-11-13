package com.ClassExchange.usecases.manter_coordenadorCurso;

import com.ClassExchange.domain.entity.CoordenadorCurso;
import com.ClassExchange.domain.entity.Curso;
import com.ClassExchange.domain.entity.Professor;
import com.ClassExchange.usecases.manter_cursos.CursoRepository;
import com.ClassExchange.usecases.manter_professores.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class CoordenadorCursoService {

    @Autowired
    private CoordenadorCursoRepository coordenadorCursoRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private CursoRepository cursoRepository;
    @Autowired
    private CoordenadorCursoMapper mapper;

    public CoordenadorCursoResponse criar(CoordenadorCursoRequest request) {
        Professor professor = professorRepository.findById(request.professorId())
                .orElseThrow(() -> new RuntimeException("Professor não encontrado com ID: " + request.professorId()));

        Curso curso = cursoRepository.findById(request.cursoId())
                .orElseThrow(() -> new RuntimeException("Curso não encontrado com ID: " + request.cursoId()));

        CoordenadorCurso coordenadorCurso = CoordenadorCurso.builder()
                .inicio(request.inicio())
                .fim(request.fim())
                .professor(professor)
                .curso(curso)
                .build();

        CoordenadorCurso coordenadorCursoSalvo = coordenadorCursoRepository.save(coordenadorCurso);
        return toResponse(coordenadorCursoSalvo);
    }

    @Transactional(readOnly = true)
    public List<CoordenadorCursoResponse> listarTodos() {
        return coordenadorCursoRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public Optional<CoordenadorCursoResponse> buscarPorId(UUID id) {
        return coordenadorCursoRepository.findById(id)
                .map(this::toResponse);
    }

    public CoordenadorCursoResponse atualizar(UUID id, CoordenadorCursoRequest request) {
        CoordenadorCurso coordenadorCurso = coordenadorCursoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CoordenadorCurso não encontrado com ID: " + id));

        Professor professor = professorRepository.findById(request.professorId())
                .orElseThrow(() -> new RuntimeException("Professor não encontrado com ID: " + request.professorId()));

        Curso curso = cursoRepository.findById(request.cursoId())
                .orElseThrow(() -> new RuntimeException("Curso não encontrado com ID: " + request.cursoId()));

        coordenadorCurso.setInicio(request.inicio());
        coordenadorCurso.setFim(request.fim());
        coordenadorCurso.setProfessor(professor);
        coordenadorCurso.setCurso(curso);

        CoordenadorCurso coordenadorCursoAtualizado = coordenadorCursoRepository.save(coordenadorCurso);
        return toResponse(coordenadorCursoAtualizado);
    }

    public void deletar(UUID id) {
        if (!coordenadorCursoRepository.existsById(id)) {
            throw new RuntimeException("CoordenadorCurso não encontrado com ID: " + id);
        }
        coordenadorCursoRepository.deleteById(id);
    }

    private CoordenadorCursoResponse toResponse(CoordenadorCurso coordenadorCurso) {
        return mapper.toResponse(coordenadorCurso);
    }
}
