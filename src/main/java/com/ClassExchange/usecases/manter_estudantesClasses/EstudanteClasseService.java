package com.ClassExchange.usecases.manter_estudantesClasses;

import com.ClassExchange.domain.entity.Classe;
import com.ClassExchange.domain.entity.Estudante;
import com.ClassExchange.domain.entity.EstudanteClasse;
import com.ClassExchange.exception.NotFoundException;
import com.ClassExchange.usecases.manter_classes.ClasseRepository;
import com.ClassExchange.usecases.manter_estudantes.EstudanteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EstudanteClasseService {

    private final EstudanteClasseRepository repository;
    private final EstudanteRepository estudanteRepository;
    private final ClasseRepository classeRepository;

    @Transactional
    public EstudanteClasseResponse criar(EstudanteClasseRequest request) {
        Estudante estudante = estudanteRepository.findById(request.estudanteId())
                .orElseThrow(() -> new NotFoundException("Estudante não encontrado"));

        Classe classe = classeRepository.findById(request.classeId())
                .orElseThrow(() -> new NotFoundException("Classe não encontrada"));

        EstudanteClasse estudanteClasse = EstudanteClasse.builder()
                .matricula(request.matricula())
                .vinculoCurso(request.vinculoCurso())
                .situacao(request.situacao())
                .estudante(estudante)
                .classe(classe)
                .build();

        return toResponse(repository.save(estudanteClasse));
    }

    @Transactional(readOnly = true)
    public List<EstudanteClasseResponse> listarTodos() {
        return repository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public Optional<EstudanteClasseResponse> buscarPorId(UUID id) {
        return repository.findById(id)
                .map(this::toResponse);
    }

    @Transactional
    public EstudanteClasseResponse atualizar(UUID id, EstudanteClasseRequest request) {
        EstudanteClasse estudanteClasse = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("EstudanteClasse não encontrado"));

        Estudante estudante = estudanteRepository.findById(request.estudanteId())
                .orElseThrow(() -> new NotFoundException("Estudante não encontrado"));

        Classe classe = classeRepository.findById(request.classeId())
                .orElseThrow(() -> new NotFoundException("Classe não encontrada"));

        estudanteClasse.setMatricula(request.matricula());
        estudanteClasse.setVinculoCurso(request.vinculoCurso());
        estudanteClasse.setSituacao(request.situacao());
        estudanteClasse.setEstudante(estudante);
        estudanteClasse.setClasse(classe);

        return toResponse(repository.save(estudanteClasse));
    }

    @Transactional
    public void deletar(UUID id) {
        if (!repository.existsById(id)) {
            throw new NotFoundException("EstudanteClasse não encontrado");
        }
        repository.deleteById(id);
    }

    private EstudanteClasseResponse toResponse(EstudanteClasse estudanteClasse) {
        return new EstudanteClasseResponse(
                estudanteClasse.getId(),
                estudanteClasse.getMatricula(),
                estudanteClasse.getVinculoCurso(),
                estudanteClasse.getSituacao(),
                estudanteClasse.getEstudante().getId(),
                estudanteClasse.getEstudante().getNome(),
                estudanteClasse.getClasse().getId(),
                "Classe " + estudanteClasse.getClasse().getId(),
                estudanteClasse.getCreatedAt(),
                estudanteClasse.getUpdatedAt()
        );
    }
}