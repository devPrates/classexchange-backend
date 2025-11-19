package com.ClassExchange.usecases.manter_professorCurso;

import com.ClassExchange.domain.entity.Curso;
import com.ClassExchange.domain.entity.ProfessorCurso;
import com.ClassExchange.domain.entity.Usuario;
import com.ClassExchange.exception.NotFoundException;
import com.ClassExchange.usecases.manter_cursos.CursoRepository;
import com.ClassExchange.usecases.manter_usuarios.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProfessorCursoService {

    private final ProfessorCursoRepository repository;
    private final UsuarioRepository usuarioRepository;
    private final CursoRepository cursoRepository;
    private final ProfessorCursoMapper mapper;

    public ProfessorCursoResponse criar(ProfessorCursoRequest request) {
        Usuario usuario = usuarioRepository.findById(request.usuarioId())
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado"));
        Curso curso = cursoRepository.findById(request.cursoId())
                .orElseThrow(() -> new NotFoundException("Curso não encontrado"));

        ProfessorCurso pc = mapper.toEntity(request, usuario, curso);
        return toResponse(repository.save(pc));
    }

    public List<ProfessorCursoResponse> listarTodos() {
        return repository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    public Optional<ProfessorCursoResponse> buscarPorId(UUID id) {
        return repository.findById(id)
                .map(this::toResponse);
    }

    public ProfessorCursoResponse atualizar(UUID id, ProfessorCursoRequest request) {
        ProfessorCurso pc = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("ProfessorCurso não encontrado"));
        Usuario usuario = usuarioRepository.findById(request.usuarioId())
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado"));
        Curso curso = cursoRepository.findById(request.cursoId())
                .orElseThrow(() -> new NotFoundException("Curso não encontrado"));

        mapper.updateEntityFromRequest(request, pc, usuario, curso);
        return toResponse(repository.save(pc));
    }

    public void deletar(UUID id) {
        if (!repository.existsById(id)) {
            throw new NotFoundException("ProfessorCurso não encontrado");
        }
        repository.deleteById(id);
    }

    private ProfessorCursoResponse toResponse(ProfessorCurso pc) {
        return mapper.toResponse(pc);
    }
}