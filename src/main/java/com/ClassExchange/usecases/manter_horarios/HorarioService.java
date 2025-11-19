package com.ClassExchange.usecases.manter_horarios;

import com.ClassExchange.domain.entity.CargaHoraria;
import com.ClassExchange.domain.entity.DisciplinaTurma;
import com.ClassExchange.domain.entity.Horario;
import com.ClassExchange.exception.NotFoundException;
import com.ClassExchange.usecases.manter_cargaHoraria.CargaHorariaRepository;
import com.ClassExchange.usecases.manter_disciplinaTurma.DisciplinaTurmaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HorarioService {

    private final HorarioRepository repository;
    private final DisciplinaTurmaRepository disciplinaTurmaRepository;
    private final CargaHorariaRepository cargaHorariaRepository;
    private final HorarioMapper mapper;

    public HorarioResponse criar(HorarioRequest request) {
        DisciplinaTurma disciplinaTurma = disciplinaTurmaRepository.findById(request.disciplinaTurmaId())
                .orElseThrow(() -> new NotFoundException("DisciplinaTurma não encontrada"));
        CargaHoraria cargaHoraria = cargaHorariaRepository.findById(request.cargaHorariaId())
                .orElseThrow(() -> new NotFoundException("CargaHoraria não encontrada"));

        Horario horario = mapper.toEntity(request, disciplinaTurma, cargaHoraria);
        return toResponse(repository.save(horario));
    }

    public List<HorarioResponse> listarTodos() {
        return repository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    public Optional<HorarioResponse> buscarPorId(UUID id) {
        return repository.findById(id)
                .map(this::toResponse);
    }

    public HorarioResponse atualizar(UUID id, HorarioRequest request) {
        Horario horario = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Horario não encontrado"));
        DisciplinaTurma disciplinaTurma = disciplinaTurmaRepository.findById(request.disciplinaTurmaId())
                .orElseThrow(() -> new NotFoundException("DisciplinaTurma não encontrada"));
        CargaHoraria cargaHoraria = cargaHorariaRepository.findById(request.cargaHorariaId())
                .orElseThrow(() -> new NotFoundException("CargaHoraria não encontrada"));

        mapper.updateEntityFromRequest(request, horario, disciplinaTurma, cargaHoraria);
        return toResponse(repository.save(horario));
    }

    public void deletar(UUID id) {
        if (!repository.existsById(id)) {
            throw new NotFoundException("Horario não encontrado");
        }
        repository.deleteById(id);
    }

    private HorarioResponse toResponse(Horario horario) {
        return mapper.toResponse(horario);
    }
}