package com.ClassExchange.usecases.manter_diretorEnsino;

import com.ClassExchange.domain.entity.Campus;
import com.ClassExchange.domain.entity.DiretorEnsino;
import com.ClassExchange.domain.entity.Professor;
import com.ClassExchange.usecases.manter_campus.CampusRepository;
import com.ClassExchange.usecases.manter_professores.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class DiretorEnsinoService {

    @Autowired
    private DiretorEnsinoRepository diretorEnsinoRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private CampusRepository campusRepository;
    @Autowired
    private DiretorEnsinoMapper mapper;

    public DiretorEnsinoResponse criar(DiretorEnsinoRequest request) {
        Professor professor = professorRepository.findById(request.professorId())
                .orElseThrow(() -> new RuntimeException("Professor não encontrado com ID: " + request.professorId()));

        Campus campus = campusRepository.findById(request.campusId())
                .orElseThrow(() -> new RuntimeException("Campus não encontrado com ID: " + request.campusId()));

        DiretorEnsino diretorEnsino = DiretorEnsino.builder()
                .inicio(request.inicio())
                .fim(request.fim())
                .professor(professor)
                .campus(campus)
                .build();

        DiretorEnsino diretorEnsinoSalvo = diretorEnsinoRepository.save(diretorEnsino);
        return toResponse(diretorEnsinoSalvo);
    }

    @Transactional(readOnly = true)
    public List<DiretorEnsinoResponse> listarTodos() {
        return diretorEnsinoRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public Optional<DiretorEnsinoResponse> buscarPorId(UUID id) {
        return diretorEnsinoRepository.findById(id)
                .map(this::toResponse);
    }

    public DiretorEnsinoResponse atualizar(UUID id, DiretorEnsinoRequest request) {
        DiretorEnsino diretorEnsino = diretorEnsinoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("DiretorEnsino não encontrado com ID: " + id));

        Professor professor = professorRepository.findById(request.professorId())
                .orElseThrow(() -> new RuntimeException("Professor não encontrado com ID: " + request.professorId()));

        Campus campus = campusRepository.findById(request.campusId())
                .orElseThrow(() -> new RuntimeException("Campus não encontrado com ID: " + request.campusId()));

        diretorEnsino.setInicio(request.inicio());
        diretorEnsino.setFim(request.fim());
        diretorEnsino.setProfessor(professor);
        diretorEnsino.setCampus(campus);

        DiretorEnsino diretorEnsinoAtualizado = diretorEnsinoRepository.save(diretorEnsino);
        return toResponse(diretorEnsinoAtualizado);
    }

    public void deletar(UUID id) {
        if (!diretorEnsinoRepository.existsById(id)) {
            throw new RuntimeException("DiretorEnsino não encontrado com ID: " + id);
        }
        diretorEnsinoRepository.deleteById(id);
    }

    private DiretorEnsinoResponse toResponse(DiretorEnsino diretorEnsino) {
        return mapper.toResponse(diretorEnsino);
    }
}
