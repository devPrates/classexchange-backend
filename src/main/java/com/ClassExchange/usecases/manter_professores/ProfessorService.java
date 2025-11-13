package com.ClassExchange.usecases.manter_professores;

import com.ClassExchange.domain.entity.Professor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class ProfessorService {

    @Autowired
    private ProfessorRepository professorRepository;
    @Autowired
    private ProfessorMapper mapper;

    public ProfessorResponse criar(ProfessorRequest request) {
        Professor professor = Professor.builder()
                .nome(request.nome())
                .email(request.email())
                .siape(request.siape())
                .celular(request.celular())
                .build();

        Professor professorSalvo = professorRepository.save(professor);
        return toResponse(professorSalvo);
    }

    @Transactional(readOnly = true)
    public List<ProfessorResponse> listarTodos() {
        return professorRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public Optional<ProfessorResponse> buscarPorId(UUID id) {
        return professorRepository.findById(id)
                .map(this::toResponse);
    }

    public ProfessorResponse atualizar(UUID id, ProfessorRequest request) {
        Professor professor = professorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Professor não encontrado com ID: " + id));

        professor.setNome(request.nome());
        professor.setEmail(request.email());
        professor.setSiape(request.siape());
        professor.setCelular(request.celular());

        Professor professorAtualizado = professorRepository.save(professor);
        return toResponse(professorAtualizado);
    }

    public void deletar(UUID id) {
        if (!professorRepository.existsById(id)) {
            throw new RuntimeException("Professor não encontrado com ID: " + id);
        }
        professorRepository.deleteById(id);
    }

    private ProfessorResponse toResponse(Professor professor) {
        return mapper.toResponse(professor);
    }
}
