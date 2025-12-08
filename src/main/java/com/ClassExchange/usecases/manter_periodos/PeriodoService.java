package com.ClassExchange.usecases.manter_periodos;

import com.ClassExchange.domain.entity.Periodo;
import com.ClassExchange.domain.entity.Turma;
import com.ClassExchange.exception.BusinessException;
import com.ClassExchange.exception.NotFoundException;
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
    private final TurmaRepository turmaRepository;
    private final PeriodoMapper mapper;

    @Transactional
    public PeriodoResponse criar(PeriodoRequest request) {
        validarDatas(request.inicio(), request.fim());
        
        Turma turma = turmaRepository.findById(request.turmaId())
                .orElseThrow(() -> new NotFoundException("Turma não encontrada com ID: " + request.turmaId()));
        
        Periodo periodo = mapper.toEntity(request, turma);
        periodo.setSlug(SlugUtils.generateSlug(request.nome()));
        
        Periodo periodoSalvo = periodoRepository.save(periodo);
        return toResponse(periodoSalvo);
    }

    @Transactional(readOnly = true)
    public List<PeriodoResponse> listarTodos() {
        if (com.ClassExchange.security.SecurityUtils.isAdmin()) {
            return periodoRepository.findAll().stream().map(this::toResponse).toList();
        }
        String campusId = com.ClassExchange.security.SecurityUtils.currentCampusId();
        if (campusId == null) {
            return java.util.List.of();
        }
        return periodoRepository.findAll().stream()
                .filter(p -> p.getTurma() != null && p.getTurma().getCurso() != null && p.getTurma().getCurso().getCampus() != null && campusId.equals(p.getTurma().getCurso().getCampus().getId().toString()))
                .map(this::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public PeriodoResponse buscarPorId(UUID id) {
        Periodo periodo = periodoRepository.findById(id).orElseThrow(() -> new NotFoundException("Período não encontrado com ID: " + id));
        if (!com.ClassExchange.security.SecurityUtils.isAdmin()) {
            String campusId = com.ClassExchange.security.SecurityUtils.currentCampusId();
            if (campusId == null || periodo.getTurma() == null || periodo.getTurma().getCurso() == null || periodo.getTurma().getCurso().getCampus() == null || !campusId.equals(periodo.getTurma().getCurso().getCampus().getId().toString())) {
                throw new NotFoundException("Período não encontrado com ID: " + id);
            }
        }
        return toResponse(periodo);
    }

    @Transactional
    public PeriodoResponse atualizar(UUID id, PeriodoRequest request) {
        validarDatas(request.inicio(), request.fim());
        
        Periodo periodo = periodoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Período não encontrado com ID: " + id));
        
        Turma turma = turmaRepository.findById(request.turmaId())
                .orElseThrow(() -> new NotFoundException("Turma não encontrada com ID: " + request.turmaId()));
        
        mapper.updateEntityFromRequest(request, periodo, turma);
        periodo.setSlug(SlugUtils.generateSlug(request.nome()));
        
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
        Optional<Periodo> opt = periodoRepository.findBySlug(slug);
        if (opt.isEmpty()) return java.util.Optional.empty();
        Periodo periodo = opt.get();
        if (!com.ClassExchange.security.SecurityUtils.isAdmin()) {
            String campusId = com.ClassExchange.security.SecurityUtils.currentCampusId();
            if (campusId == null || periodo.getTurma() == null || periodo.getTurma().getCurso() == null || periodo.getTurma().getCurso().getCampus() == null || !campusId.equals(periodo.getTurma().getCurso().getCampus().getId().toString())) {
                return java.util.Optional.empty();
            }
        }
        return java.util.Optional.of(this.toResponse(periodo));
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
