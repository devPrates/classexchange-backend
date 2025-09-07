package com.ClassExchange.usecases.manter_campus;

import com.ClassExchange.domain.entity.Campus;
import com.ClassExchange.exception.NotFoundException;
import com.ClassExchange.usecases.manter_cursos.CursoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CampusService {

    private final CampusRepository repository;
    private final CursoRepository cursoRepository;

    public CampusResponse criar(CampusRequest request) {
        Campus campus = Campus.builder()
                .nome(request.nome())
                .sigla(request.sigla())
                .email(request.email())
                .telefone(request.telefone())
                .endereco(request.endereco())
                .build();

        return toResponse(repository.save(campus));
    }

    public List<CampusResponse> listarTodos() {
        return repository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    public Optional<CampusResponse> buscarPorId(UUID id) {
        return repository.findById(id)
                .map(this::toResponse);
    }

    public CampusResponse atualizar(UUID id, CampusRequest request) {
        Campus campus = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Campus não encontrado"));

        campus.setNome(request.nome());
        campus.setSigla(request.sigla());
        campus.setEmail(request.email());
        campus.setTelefone(request.telefone());
        campus.setEndereco(request.endereco());

        return toResponse(repository.save(campus));
    }

    public void deletar(UUID id) {
        if (!repository.existsById(id)) {
            throw new NotFoundException("Campus não encontrado");
        }
        repository.deleteById(id);
    }

    private CampusResponse toResponse(Campus campus) {
        List<CursoSimplificadoResponse> cursos = cursoRepository.findByCampus(campus)
                .stream()
                .map(curso -> new CursoSimplificadoResponse(
                        curso.getId(),
                        curso.getNome(),
                        curso.getSigla()
                ))
                .toList();

        return new CampusResponse(
                campus.getId(),
                campus.getNome(),
                campus.getSigla(),
                campus.getEmail(),
                campus.getTelefone(),
                campus.getEndereco(),
                cursos,
                campus.getCreatedAt(),
                campus.getUpdatedAt()
        );
    }
}
