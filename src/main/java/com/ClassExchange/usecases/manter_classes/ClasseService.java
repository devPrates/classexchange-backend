package com.ClassExchange.usecases.manter_classes;

import com.ClassExchange.domain.entity.*;
import com.ClassExchange.exception.BusinessException;
import com.ClassExchange.exception.NotFoundException;
import com.ClassExchange.usecases.manter_disciplinas.DisciplinaRepository;
import com.ClassExchange.usecases.manter_turmas.TurmaRepository;
import com.ClassExchange.usecases.manter_cargaHoraria.CargaHorariaRepository;
import com.ClassExchange.usecases.manter_locais.LocalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClasseService {

    private final ClasseRepository classeRepository;
    private final DisciplinaRepository disciplinaRepository;
    private final TurmaRepository turmaRepository;
    private final CargaHorariaRepository cargaHorariaRepository;
    private final LocalRepository localRepository;
    private final ClasseMapper mapper;

    @Transactional
    public ClasseResponse criar(ClasseRequest request) {
        validarDataInicio(request.inicio());
        
        Disciplina disciplina = disciplinaRepository.findById(request.disciplinaId())
                .orElseThrow(() -> new NotFoundException("Disciplina não encontrada com ID: " + request.disciplinaId()));
        
        Turma turma = turmaRepository.findById(request.turmaId())
                .orElseThrow(() -> new NotFoundException("Turma não encontrada com ID: " + request.turmaId()));
        
        CargaHoraria cargaHoraria = cargaHorariaRepository.findById(request.cargaHorariaId())
                .orElseThrow(() -> new NotFoundException("Carga horária não encontrada com ID: " + request.cargaHorariaId()));
        
        Local local = localRepository.findById(request.localId())
                .orElseThrow(() -> new NotFoundException("Local não encontrado com ID: " + request.localId()));
        
        Classe classe = Classe.builder()
                .vagas(request.vagas())
                .numeroAulas(request.numeroAulas())
                .inicio(request.inicio())
                .disciplina(disciplina)
                .turma(turma)
                .cargaHoraria(cargaHoraria)
                .local(local)
                .build();
        
        Classe classeSalva = classeRepository.save(classe);
        return toResponse(classeSalva);
    }

    @Transactional(readOnly = true)
    public List<ClasseResponse> listarTodos() {
        return classeRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public Optional<ClasseResponse> buscarPorId(UUID id) {
        return classeRepository.findById(id)
                .map(this::toResponse);
    }

    @Transactional
    public ClasseResponse atualizar(UUID id, ClasseRequest request) {
        validarDataInicio(request.inicio());
        
        Classe classe = classeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Classe não encontrada com ID: " + id));
        
        Disciplina disciplina = disciplinaRepository.findById(request.disciplinaId())
                .orElseThrow(() -> new NotFoundException("Disciplina não encontrada com ID: " + request.disciplinaId()));
        
        Turma turma = turmaRepository.findById(request.turmaId())
                .orElseThrow(() -> new NotFoundException("Turma não encontrada com ID: " + request.turmaId()));
        
        CargaHoraria cargaHoraria = cargaHorariaRepository.findById(request.cargaHorariaId())
                .orElseThrow(() -> new NotFoundException("Carga horária não encontrada com ID: " + request.cargaHorariaId()));
        
        Local local = localRepository.findById(request.localId())
                .orElseThrow(() -> new NotFoundException("Local não encontrado com ID: " + request.localId()));
        
        classe.setVagas(request.vagas());
        classe.setNumeroAulas(request.numeroAulas());
        classe.setInicio(request.inicio());
        classe.setDisciplina(disciplina);
        classe.setTurma(turma);
        classe.setCargaHoraria(cargaHoraria);
        classe.setLocal(local);
        
        Classe classeAtualizada = classeRepository.save(classe);
        return toResponse(classeAtualizada);
    }

    @Transactional
    public void deletar(UUID id) {
        Classe classe = classeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Classe não encontrada com ID: " + id));
        
        classeRepository.delete(classe);
    }

    private void validarDataInicio(LocalDate inicio) {
        if (inicio.isBefore(LocalDate.now())) {
            throw new BusinessException("Data de início não pode ser anterior à data atual");
        }
    }

    private ClasseResponse toResponse(Classe classe) {
        return mapper.toResponse(classe);
    }
}
