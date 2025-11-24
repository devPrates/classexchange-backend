package com.ClassExchange.usecases.manter_horarios;

import com.ClassExchange.domain.entity.Horario;
import com.ClassExchange.domain.entity.Aula;
import com.ClassExchange.exception.NotFoundException;
import com.ClassExchange.usecases.manter_aulas.AulaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HorarioService {

    private final HorarioRepository repository;
    private final AulaRepository aulaRepository;
    private final HorarioMapper mapper;

    public HorarioResponse criar(HorarioRequest request) {
        Aula aula = aulaRepository.findById(request.aulaId())
                .orElseThrow(() -> new NotFoundException("Aula não encontrada"));

        Horario horario = mapper.toEntity(request, aula);
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
        Aula aula = aulaRepository.findById(request.aulaId())
                .orElseThrow(() -> new NotFoundException("Aula não encontrada"));

        mapper.updateEntityFromRequest(request, horario, aula);
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
