package com.ClassExchange.usecases.manter_aulas;

import com.ClassExchange.domain.entity.*;
import org.springframework.stereotype.Component;

@Component
public class AulaMapper {
    public AulaResponse toResponse(Aula aula) {
        return new AulaResponse(
                aula.getId(),
                aula.getPeriodo() != null ? aula.getPeriodo().getId() : null,
                aula.getPeriodo() != null ? aula.getPeriodo().getNome() : null,
                aula.getDisciplina() != null ? aula.getDisciplina().getId() : null,
                aula.getDisciplina() != null ? aula.getDisciplina().getNome() : null,
                aula.getProfessor() != null ? aula.getProfessor().getId() : null,
                aula.getProfessor() != null ? aula.getProfessor().getNome() : null,
                aula.getLocal() != null ? aula.getLocal().getId() : null,
                aula.getLocal() != null ? aula.getLocal().getNome() : null,
                aula.getCreatedAt(),
                aula.getUpdatedAt()
        );
    }

    public Aula toEntity(AulaRequest request, Periodo periodo, Disciplina disciplina, Usuario professor, Local local) {
        Aula aula = new Aula();
        aula.setPeriodo(periodo);
        aula.setDisciplina(disciplina);
        aula.setProfessor(professor);
        aula.setLocal(local);
        return aula;
    }

    public void updateEntityFromRequest(AulaRequest request, Aula aula, Periodo periodo, Disciplina disciplina, Usuario professor, Local local) {
        aula.setPeriodo(periodo);
        aula.setDisciplina(disciplina);
        aula.setProfessor(professor);
        aula.setLocal(local);
    }
}

