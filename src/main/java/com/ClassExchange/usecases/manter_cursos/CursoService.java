package com.ClassExchange.usecases.manter_cursos;

import com.ClassExchange.domain.entity.Campus;
import com.ClassExchange.domain.entity.Curso;
import com.ClassExchange.exception.NotFoundException;
import com.ClassExchange.usecases.manter_campus.CampusRepository;
import com.ClassExchange.usecases.manter_coordenadorCurso.CoordenadorCursoRepository;
import com.ClassExchange.usecases.manter_turmas.TurmaRepository;
import com.ClassExchange.usecases.manter_estudanteCurso.EstudanteCursoRepository;
import com.ClassExchange.utils.SlugUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CursoService {

    private final CursoRepository repository;
    private final CampusRepository campusRepository;
    private final CoordenadorCursoRepository coordenadorCursoRepository;
    private final TurmaRepository turmaRepository;
    private final EstudanteCursoRepository estudanteCursoRepository;
    private final com.ClassExchange.usecases.manter_periodos.PeriodoRepository periodoRepository;
    private final com.ClassExchange.usecases.manter_periodos.PeriodoMapper periodoMapper;
    private final CursoMapper mapper;

    public CursoResponse criar(CursoRequest request) {
        Campus campus = campusRepository.findById(request.campusId())
                .orElseThrow(() -> new NotFoundException("Campus não encontrado"));

        Curso curso = Curso.builder()
                .nome(request.nome())
                .sigla(request.sigla())
                .slug(SlugUtils.generateSlug(request.nome()))
                .campus(campus)
                .build();

        return toResponse(repository.save(curso));
    }

    public List<CursoResponse> listarTodos() {
        return repository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    public Optional<CursoResponse> buscarPorId(UUID id) {
        return repository.findById(id)
                .map(this::toResponse);
    }

    public Optional<CursoResponse> buscarPorSlug(String slug) {
        return repository.findBySlug(slug)
                .map(this::toResponse);
    }

    public CursoResponse atualizar(UUID id, CursoRequest request) {
        Curso curso = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Curso não encontrado"));

        Campus campus = campusRepository.findById(request.campusId())
                .orElseThrow(() -> new NotFoundException("Campus não encontrado"));

        curso.setNome(request.nome());
        curso.setSigla(request.sigla());
        curso.setSlug(SlugUtils.generateSlug(request.nome()));
        curso.setCampus(campus);

        return toResponse(repository.save(curso));
    }

    public void deletar(UUID id) {
        if (!repository.existsById(id)) {
            throw new NotFoundException("Curso não encontrado");
        }
        repository.deleteById(id);
    }

    private CursoResponse toResponse(Curso curso) {
        List<CursoResponse.TurmaSimplificada> turmas = turmaRepository.findByCurso(curso)
                .stream()
                .map(mapper::toTurmaSimplificada)
                .toList();

        CursoResponse.CoordenadorSimplificado coordenador = coordenadorCursoRepository
                .findByCurso(curso).stream()
                .sorted((a,b) -> {
                    java.time.LocalDate af = a.getFim() == null ? java.time.LocalDate.MAX : a.getFim();
                    java.time.LocalDate bf = b.getFim() == null ? java.time.LocalDate.MAX : b.getFim();
                    return bf.compareTo(af);
                })
                .findFirst()
                .map(mapper::toCoordenadorSimplificado)
                .orElse(null);

        long studentsCount = estudanteCursoRepository.countByCursoId(curso.getId());
        return mapper.toResponse(curso, turmas, coordenador, studentsCount);
    }

    public java.util.List<com.ClassExchange.usecases.manter_cursos.CursoResponse.EstudanteSimplificado> listarEstudantesDoCurso(java.util.UUID id) {
        Curso curso = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Curso não encontrado"));
        return estudanteCursoRepository.findByCursoId(curso.getId()).stream()
                .map(mapper::toEstudanteSimplificado)
                .toList();
    }

    public java.util.List<com.ClassExchange.usecases.manter_periodos.PeriodoCursoResponse> listarPeriodosDoCurso(java.util.UUID id) {
        Curso curso = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Curso não encontrado"));
        return periodoRepository.findByCursoId(curso.getId()).stream()
                .map(periodoMapper::toCursoResponse)
                .toList();
    }
}