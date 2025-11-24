package com.ClassExchange.usecases.manter_aulas;

import com.ClassExchange.domain.entity.*;
import com.ClassExchange.exception.NotFoundException;
import com.ClassExchange.usecases.manter_periodos.PeriodoRepository;
import com.ClassExchange.usecases.manter_disciplinas.DisciplinaRepository;
import com.ClassExchange.usecases.manter_usuarios.UsuarioRepository;
import com.ClassExchange.usecases.manter_locais.LocalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AulaService {

    private final AulaRepository repository;
    private final PeriodoRepository periodoRepository;
    private final DisciplinaRepository disciplinaRepository;
    private final UsuarioRepository usuarioRepository;
    private final LocalRepository localRepository;
    private final AulaMapper mapper;

    public AulaResponse criar(AulaRequest request) {
        Periodo periodo = periodoRepository.findById(request.periodoId()).orElseThrow(() -> new NotFoundException("Período não encontrado"));
        Disciplina disciplina = disciplinaRepository.findById(request.disciplinaId()).orElseThrow(() -> new NotFoundException("Disciplina não encontrada"));
        Usuario professor = usuarioRepository.findById(request.professorId()).orElseThrow(() -> new NotFoundException("Professor não encontrado"));
        Local local = localRepository.findById(request.localId()).orElseThrow(() -> new NotFoundException("Local não encontrado"));
        Aula aula = mapper.toEntity(request, periodo, disciplina, professor, local);
        return mapper.toResponse(repository.save(aula));
    }

    public List<AulaResponse> listarTodos() {
        return repository.findAll().stream().map(mapper::toResponse).toList();
    }

    public Optional<AulaResponse> buscarPorId(UUID id) {
        return repository.findById(id).map(mapper::toResponse);
    }

    public AulaResponse atualizar(UUID id, AulaRequest request) {
        Aula aula = repository.findById(id).orElseThrow(() -> new NotFoundException("Aula não encontrada"));
        Periodo periodo = periodoRepository.findById(request.periodoId()).orElseThrow(() -> new NotFoundException("Período não encontrado"));
        Disciplina disciplina = disciplinaRepository.findById(request.disciplinaId()).orElseThrow(() -> new NotFoundException("Disciplina não encontrada"));
        Usuario professor = usuarioRepository.findById(request.professorId()).orElseThrow(() -> new NotFoundException("Professor não encontrado"));
        Local local = localRepository.findById(request.localId()).orElseThrow(() -> new NotFoundException("Local não encontrado"));
        mapper.updateEntityFromRequest(request, aula, periodo, disciplina, professor, local);
        return mapper.toResponse(repository.save(aula));
    }

    public void deletar(UUID id) {
        if (!repository.existsById(id)) {
            throw new NotFoundException("Aula não encontrada");
        }
        repository.deleteById(id);
    }
}

