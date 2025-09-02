package com.ClassExchange.usecases.manter_professorClasse;

import com.ClassExchange.domain.entity.Classe;
import com.ClassExchange.domain.entity.Professor;
import com.ClassExchange.domain.entity.ProfessorClasse;
import com.ClassExchange.usecases.manter_professores.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
interface ClasseRepository extends JpaRepository<Classe, UUID> {
}

@Service
@Transactional
public class ProfessorClasseService {

    @Autowired
    private ProfessorClasseRepository professorClasseRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private ClasseRepository classeRepository;

    public ProfessorClasseResponse criar(ProfessorClasseRequest request) {
        Professor professor = professorRepository.findById(request.professorId())
                .orElseThrow(() -> new RuntimeException("Professor não encontrado com ID: " + request.professorId()));

        Classe classe = classeRepository.findById(request.classeId())
                .orElseThrow(() -> new RuntimeException("Classe não encontrada com ID: " + request.classeId()));

        ProfessorClasse professorClasse = ProfessorClasse.builder()
                .inicio(request.inicio())
                .fim(request.fim())
                .professor(professor)
                .classe(classe)
                .build();

        ProfessorClasse professorClasseSalva = professorClasseRepository.save(professorClasse);
        return toResponse(professorClasseSalva);
    }

    @Transactional(readOnly = true)
    public List<ProfessorClasseResponse> listarTodos() {
        return professorClasseRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public Optional<ProfessorClasseResponse> buscarPorId(UUID id) {
        return professorClasseRepository.findById(id)
                .map(this::toResponse);
    }

    public ProfessorClasseResponse atualizar(UUID id, ProfessorClasseRequest request) {
        ProfessorClasse professorClasse = professorClasseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ProfessorClasse não encontrada com ID: " + id));

        Professor professor = professorRepository.findById(request.professorId())
                .orElseThrow(() -> new RuntimeException("Professor não encontrado com ID: " + request.professorId()));

        Classe classe = classeRepository.findById(request.classeId())
                .orElseThrow(() -> new RuntimeException("Classe não encontrada com ID: " + request.classeId()));

        professorClasse.setInicio(request.inicio());
        professorClasse.setFim(request.fim());
        professorClasse.setProfessor(professor);
        professorClasse.setClasse(classe);

        ProfessorClasse professorClasseAtualizada = professorClasseRepository.save(professorClasse);
        return toResponse(professorClasseAtualizada);
    }

    public void deletar(UUID id) {
        if (!professorClasseRepository.existsById(id)) {
            throw new RuntimeException("ProfessorClasse não encontrada com ID: " + id);
        }
        professorClasseRepository.deleteById(id);
    }

    private ProfessorClasseResponse toResponse(ProfessorClasse professorClasse) {
        return new ProfessorClasseResponse(
                professorClasse.getId(),
                professorClasse.getInicio(),
                professorClasse.getFim(),
                professorClasse.getProfessor().getId(),
                professorClasse.getProfessor().getNome(),
                professorClasse.getClasse().getId(),
                "TODO: Implementar nome da classe", // TODO: Implementar método para obter nome da classe
                professorClasse.getCreatedAt(),
                professorClasse.getUpdatedAt()
        );
    }
}