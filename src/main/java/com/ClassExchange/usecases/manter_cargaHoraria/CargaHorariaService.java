package com.ClassExchange.usecases.manter_cargaHoraria;

import com.ClassExchange.domain.entity.CargaHoraria;
import com.ClassExchange.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CargaHorariaService {

    private final CargaHorariaRepository repository;

    @Transactional
    public CargaHorariaResponse criar(CargaHorariaRequest request) {
        CargaHoraria cargaHoraria = CargaHoraria.builder()
                .nome(request.nome())
                .duracao(request.duracao())
                .medidaTempo(request.medidaTempo())
                .build();

        return toResponse(repository.save(cargaHoraria));
    }

    public List<CargaHorariaResponse> listarTodos() {
        return repository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    public Optional<CargaHorariaResponse> buscarPorId(UUID id) {
        return repository.findById(id)
                .map(this::toResponse);
    }

    @Transactional
    public CargaHorariaResponse atualizar(UUID id, CargaHorariaRequest request) {
        CargaHoraria cargaHoraria = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Carga horária não encontrada"));

        cargaHoraria.setNome(request.nome());
        cargaHoraria.setDuracao(request.duracao());
        cargaHoraria.setMedidaTempo(request.medidaTempo());

        return toResponse(repository.save(cargaHoraria));
    }

    @Transactional
    public void deletar(UUID id) {
        if (!repository.existsById(id)) {
            throw new NotFoundException("Carga horária não encontrada");
        }
        repository.deleteById(id);
    }

    private CargaHorariaResponse toResponse(CargaHoraria cargaHoraria) {
        return new CargaHorariaResponse(
                cargaHoraria.getId(),
                cargaHoraria.getNome(),
                cargaHoraria.getDuracao(),
                cargaHoraria.getMedidaTempo(),
                cargaHoraria.getCreatedAt(),
                cargaHoraria.getUpdatedAt()
        );
    }
}