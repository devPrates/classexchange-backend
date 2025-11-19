package com.ClassExchange.usecases.manter_professorDisciplina;

import com.ClassExchange.domain.entity.DisciplinaTurma;
import com.ClassExchange.domain.entity.ProfessorDisciplina;
import com.ClassExchange.domain.entity.Usuario;
import com.ClassExchange.exception.NotFoundException;
import com.ClassExchange.usecases.manter_disciplinaTurma.DisciplinaTurmaRepository;
import com.ClassExchange.usecases.manter_usuarios.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProfessorDisciplinaService {

    private final ProfessorDisciplinaRepository repository;
    private final UsuarioRepository usuarioRepository;
    private final DisciplinaTurmaRepository disciplinaTurmaRepository;
    private final ProfessorDisciplinaMapper mapper;

    public ProfessorDisciplinaResponse criar(ProfessorDisciplinaRequest request) {
        Usuario usuario = usuarioRepository.findById(request.usuarioId())
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado"));
        DisciplinaTurma disciplinaTurma = disciplinaTurmaRepository.findById(request.disciplinaTurmaId())
                .orElseThrow(() -> new NotFoundException("DisciplinaTurma não encontrada"));

        ProfessorDisciplina pd = mapper.toEntity(request, usuario, disciplinaTurma);
        return toResponse(repository.save(pd));
    }

    public List<ProfessorDisciplinaResponse> listarTodos() {
        return repository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    public Optional<ProfessorDisciplinaResponse> buscarPorId(UUID id) {
        return repository.findById(id)
                .map(this::toResponse);
    }

    public ProfessorDisciplinaResponse atualizar(UUID id, ProfessorDisciplinaRequest request) {
        ProfessorDisciplina pd = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("ProfessorDisciplina não encontrado"));
        Usuario usuario = usuarioRepository.findById(request.usuarioId())
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado"));
        DisciplinaTurma disciplinaTurma = disciplinaTurmaRepository.findById(request.disciplinaTurmaId())
                .orElseThrow(() -> new NotFoundException("DisciplinaTurma não encontrada"));

        mapper.updateEntityFromRequest(request, pd, usuario, disciplinaTurma);
        return toResponse(repository.save(pd));
    }

    public void deletar(UUID id) {
        if (!repository.existsById(id)) {
            throw new NotFoundException("ProfessorDisciplina não encontrado");
        }
        repository.deleteById(id);
    }

    private ProfessorDisciplinaResponse toResponse(ProfessorDisciplina pd) {
        return mapper.toResponse(pd);
    }
}