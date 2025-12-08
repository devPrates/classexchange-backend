package com.ClassExchange.usecases.manter_campus;

import com.ClassExchange.domain.entity.Campus;
import com.ClassExchange.domain.entity.CoordenadorCurso;
import com.ClassExchange.domain.entity.DiretorEnsino;
import com.ClassExchange.exception.NotFoundException;
import com.ClassExchange.usecases.manter_cursos.CursoRepository;
import com.ClassExchange.usecases.manter_coordenadorCurso.CoordenadorCursoRepository;
import com.ClassExchange.usecases.manter_usuarios.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Optional;
import java.util.Comparator;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CampusService {

    private final CampusRepository repository;
    private final CursoRepository cursoRepository;
    private final CoordenadorCursoRepository coordenadorCursoRepository;
    private final UsuarioRepository usuarioRepository;
    private final CampusMapper mapper;

    public CampusResponse criar(@NonNull CampusRequest request) {
        Campus campus = mapper.toEntity(request);
        return toResponse(repository.save(campus));
    }

    public List<CampusResponse> listarTodos() {
        if (com.ClassExchange.security.SecurityUtils.isAdmin()) {
            return repository.findAll().stream()
                    .map(this::toResponse)
                    .toList();
        }
        String campusId = com.ClassExchange.security.SecurityUtils.currentCampusId();
        if (campusId == null) {
            return java.util.List.of();
        }
        java.util.UUID id = java.util.UUID.fromString(campusId);
        return repository.findById(id).map(this::toResponse).map(java.util.List::of).orElse(java.util.List.of());
    }

    public Optional<CampusResponse> buscarPorId(@NonNull UUID id) {
        if (!com.ClassExchange.security.SecurityUtils.isAdmin()) {
            String campusId = com.ClassExchange.security.SecurityUtils.currentCampusId();
            if (campusId == null || !id.toString().equals(campusId)) {
                return java.util.Optional.empty();
            }
        }
        return repository.findById(id).map(this::toResponse);
    }

    public Optional<CampusResponse> buscarPorSlug(@NonNull String slug) {
        java.util.Optional<Campus> campusOpt = repository.findBySlug(slug);
        if (campusOpt.isEmpty()) return java.util.Optional.empty();
        Campus campus = campusOpt.get();
        if (!com.ClassExchange.security.SecurityUtils.isAdmin()) {
            String campusId = com.ClassExchange.security.SecurityUtils.currentCampusId();
            if (campusId == null || !campus.getId().toString().equals(campusId)) {
                return java.util.Optional.empty();
            }
        }
        return java.util.Optional.of(this.toResponse(campus));
    }

    public CampusResponse atualizar(@NonNull UUID id, @NonNull CampusRequest request) {
        Campus campus = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Campus não encontrado"));

        mapper.updateEntityFromRequest(request, campus);
        return toResponse(repository.save(java.util.Objects.requireNonNull(campus)));
    }

    public void deletar(@NonNull UUID id) {
        if (!repository.existsById(id)) {
            throw new NotFoundException("Campus não encontrado");
        }
        repository.deleteById(id);
    }

    public List<CampusResponse.UsuarioSimplificado> listarUsuariosDoCampus(@NonNull UUID campusId) {
        if (!repository.existsById(campusId)) {
            throw new NotFoundException("Campus não encontrado");
        }
        if (!com.ClassExchange.security.SecurityUtils.isAdmin()) {
            String cid = com.ClassExchange.security.SecurityUtils.currentCampusId();
            if (cid == null || !campusId.toString().equals(cid)) {
                return java.util.List.of();
            }
        }
        return usuarioRepository.findByCampusId(campusId).stream().map(mapper::toUsuarioSimplificado).toList();
    }

    private CampusResponse toResponse(Campus campus) {
        List<CampusResponse.CursoSimplificado> cursos = cursoRepository.findByCampus(campus)
                .stream()
                .map(curso -> {
                    Optional<CoordenadorCurso> coord = coordenadorCursoRepository.findByCurso(curso)
                            .stream()
                            .filter(cc -> cc.getFim() == null)
                            .max(Comparator.comparing(CoordenadorCurso::getInicio));
                    CampusResponse.CoordenadorCursoSimplificado coordDto = coord
                            .map(mapper::toCoordenadorSimplificado)
                            .orElse(null);
                    CampusResponse.CursoSimplificado cs = mapper.toCursoSimplificado(curso);
                    return new CampusResponse.CursoSimplificado(
                            cs.id(), cs.nome(), cs.sigla(), coordDto
                    );
                })
                .toList();

        Optional<DiretorEnsino> diretorAtual = Optional.ofNullable(campus.getDiretorEnsino())
                .orElse(List.of())
                .stream()
                .filter(de -> de.getFim() == null)
                .max(Comparator.comparing(DiretorEnsino::getInicio));

        CampusResponse.DiretorEnsinoSimplificado diretorDto = diretorAtual
                .map(mapper::toDiretorSimplificado)
                .orElse(null);

        long usuariosCount = usuarioRepository.countByCampusId(campus.getId());
        return mapper.toResponseWithCursosAndDiretor(campus, cursos, diretorDto, usuariosCount);
    }

    
}
