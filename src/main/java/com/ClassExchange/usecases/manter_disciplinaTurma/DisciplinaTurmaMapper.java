package com.ClassExchange.usecases.manter_disciplinaTurma;

import com.ClassExchange.domain.entity.Disciplina;
import com.ClassExchange.domain.entity.DisciplinaTurma;
import com.ClassExchange.domain.entity.Local;
import com.ClassExchange.domain.entity.Turma;
import org.springframework.stereotype.Component;

@Component
public class DisciplinaTurmaMapper {

    public DisciplinaTurmaResponse toResponse(DisciplinaTurma disciplinaTurma) {
        return new DisciplinaTurmaResponse(
                disciplinaTurma.getId(),
                disciplinaTurma.getDisciplina() != null ? disciplinaTurma.getDisciplina().getId() : null,
                disciplinaTurma.getDisciplina() != null ? disciplinaTurma.getDisciplina().getNome() : null,
                disciplinaTurma.getTurma() != null ? disciplinaTurma.getTurma().getId() : null,
                disciplinaTurma.getTurma() != null ? disciplinaTurma.getTurma().getNome() : null,
                disciplinaTurma.getLocal() != null ? disciplinaTurma.getLocal().getId() : null,
                disciplinaTurma.getLocal() != null ? disciplinaTurma.getLocal().getNome() : null,
                disciplinaTurma.getCreatedAt(),
                disciplinaTurma.getUpdatedAt()
        );
    }

    public DisciplinaTurma toEntity(DisciplinaTurmaRequest request, Disciplina disciplina, Turma turma, Local local) {
        DisciplinaTurma dt = new DisciplinaTurma();
        dt.setDisciplina(disciplina);
        dt.setTurma(turma);
        dt.setLocal(local);
        return dt;
    }

    public void updateEntityFromRequest(DisciplinaTurmaRequest request, DisciplinaTurma disciplinaTurma, Disciplina disciplina, Turma turma, Local local) {
        disciplinaTurma.setDisciplina(disciplina);
        disciplinaTurma.setTurma(turma);
        disciplinaTurma.setLocal(local);
    }
}