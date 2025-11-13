package com.ClassExchange.usecases.manter_periodos;

import com.ClassExchange.domain.entity.Disciplina;
import com.ClassExchange.domain.entity.Periodo;
import com.ClassExchange.domain.entity.Turma;
import com.ClassExchange.exception.BusinessException;
import com.ClassExchange.exception.NotFoundException;
import com.ClassExchange.usecases.manter_disciplinas.DisciplinaRepository;
import com.ClassExchange.usecases.manter_turmas.TurmaRepository;
import com.ClassExchange.utils.SlugUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PeriodoService {

    private final PeriodoRepository periodoRepository;
    private final DisciplinaRepository disciplinaRepository;
    private final TurmaRepository turmaRepository;
    private final PeriodoMapper mapper;

    @Transactional
    public PeriodoResponse criar(PeriodoRequest request) {
        validarDatas(request.inicio(), request.fim());
        
        Disciplina disciplina = disciplinaRepository.findById(request.disciplinaId())
                .orElseThrow(() -> new NotFoundException("Disciplina não encontrada com ID: " + request.disciplinaId()));
        
        Turma turma = turmaRepository.findById(request.turmaId())
                .orElseThrow(() -> new NotFoundException("Turma não encontrada com ID: " + request.turmaId()));
        
        Periodo periodo = Periodo.builder()
                .nome(request.nome())
                .slug(SlugUtils.generateSlug(request.nome()))
                .tipoPeriodo(request.tipoPeriodo())
                .numero(request.numero())
                .ano(request.ano())
                .inicio(request.inicio())
                .fim(request.fim())
                .disciplina(disciplina)
                .turma(turma)
                .build();
        
        Periodo periodoSalvo = periodoRepository.save(periodo);
        return toResponse(periodoSalvo);
    }

    @Transactional(readOnly = true)
    public List<PeriodoResponse> listarTodos() {
        return periodoRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public PeriodoResponse buscarPorId(UUID id) {
        Periodo periodo = periodoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Período não encontrado com ID: " + id));
        return toResponse(periodo);
    }

    @Transactional
    public PeriodoResponse atualizar(UUID id, PeriodoRequest request) {
        validarDatas(request.inicio(), request.fim());
        
        Periodo periodo = periodoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Período não encontrado com ID: " + id));
        
        Disciplina disciplina = disciplinaRepository.findById(request.disciplinaId())
                .orElseThrow(() -> new NotFoundException("Disciplina não encontrada com ID: " + request.disciplinaId()));
        
        Turma turma = turmaRepository.findById(request.turmaId())
                .orElseThrow(() -> new NotFoundException("Turma não encontrada com ID: " + request.turmaId()));
        
        periodo.setNome(request.nome());
        periodo.setSlug(SlugUtils.generateSlug(request.nome()));
        periodo.setTipoPeriodo(request.tipoPeriodo());
        periodo.setNumero(request.numero());
        periodo.setAno(request.ano());
        periodo.setInicio(request.inicio());
        periodo.setFim(request.fim());
        periodo.setDisciplina(disciplina);
        periodo.setTurma(turma);
        
        Periodo periodoAtualizado = periodoRepository.save(periodo);
        return toResponse(periodoAtualizado);
    }

    @Transactional
    public void deletar(UUID id) {
        Periodo periodo = periodoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Período não encontrado com ID: " + id));
        
        periodoRepository.delete(periodo);
    }

    public Optional<PeriodoResponse> buscarPorSlug(String slug) {
        return periodoRepository.findBySlug(slug)
                .map(this::toResponse);
    }

    private void validarDatas(LocalDate inicio, LocalDate fim) {
        if (inicio.isAfter(fim)) {
            throw new BusinessException("Data de início não pode ser posterior à data de fim");
        }
    }

    private PeriodoResponse toResponse(Periodo periodo) {
        return mapper.toResponse(periodo);
    }
}
