package com.ClassExchange.usecases.manter_campus;

import com.ClassExchange.domain.entity.Campus;
import com.ClassExchange.exception.NotFoundException;
import com.ClassExchange.usecases.manter_cursos.CursoRepository;
import com.ClassExchange.utils.SlugUtils;
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
    private final CampusMapper mapper;

    public CampusResponse criar(CampusRequest request) {
        Campus campus = Campus.builder()
                .nome(request.nome())
                .sigla(request.sigla())
                .email(request.email())
                .slug(SlugUtils.generateSlug(request.nome()))
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

    public Optional<CampusResponse> buscarPorSlug(String slug) {
        return repository.findBySlug(slug)
                .map(this::toResponse);
    }

    public CampusResponse atualizar(UUID id, CampusRequest request) {
        Campus campus = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Campus não encontrado"));

        campus.setNome(request.nome());
        campus.setSigla(request.sigla());
        campus.setEmail(request.email());
        campus.setSlug(SlugUtils.generateSlug(request.nome()));
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
        List<CampusResponse.CursoSimplificado> cursos = cursoRepository.findByCampus(campus)
                .stream()
                .map(mapper::toCursoSimplificado)
                .toList();

        return mapper.toResponseWithCursos(campus, cursos);
    }
}
