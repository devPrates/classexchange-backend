package com.ClassExchange.usecases.manter_professorDisciplina;

import com.ClassExchange.domain.entity.DisciplinaTurma;
import com.ClassExchange.domain.entity.ProfessorDisciplina;
import com.ClassExchange.domain.entity.Usuario;
import org.springframework.stereotype.Component;

@Component
public class ProfessorDisciplinaMapper {

    public ProfessorDisciplinaResponse toResponse(ProfessorDisciplina professorDisciplina) {
        return new ProfessorDisciplinaResponse(
                professorDisciplina.getId(),
                professorDisciplina.getInicio(),
                professorDisciplina.getFim(),
                professorDisciplina.getUsuario() != null ? professorDisciplina.getUsuario().getId() : null,
                professorDisciplina.getUsuario() != null ? professorDisciplina.getUsuario().getNome() : null,
                professorDisciplina.getDisciplinaTurma() != null ? professorDisciplina.getDisciplinaTurma().getId() : null,
                professorDisciplina.getDisciplinaTurma() != null && professorDisciplina.getDisciplinaTurma().getDisciplina() != null ? professorDisciplina.getDisciplinaTurma().getDisciplina().getNome() : null,
                professorDisciplina.getDisciplinaTurma() != null && professorDisciplina.getDisciplinaTurma().getTurma() != null ? professorDisciplina.getDisciplinaTurma().getTurma().getNome() : null,
                professorDisciplina.getCreatedAt(),
                professorDisciplina.getUpdatedAt()
        );
    }

    public ProfessorDisciplina toEntity(ProfessorDisciplinaRequest request, Usuario usuario, DisciplinaTurma disciplinaTurma) {
        ProfessorDisciplina pd = new ProfessorDisciplina();
        pd.setInicio(request.inicio());
        pd.setFim(request.fim());
        pd.setUsuario(usuario);
        pd.setDisciplinaTurma(disciplinaTurma);
        return pd;
    }

    public void updateEntityFromRequest(ProfessorDisciplinaRequest request, ProfessorDisciplina professorDisciplina, Usuario usuario, DisciplinaTurma disciplinaTurma) {
        professorDisciplina.setInicio(request.inicio());
        professorDisciplina.setFim(request.fim());
        professorDisciplina.setUsuario(usuario);
        professorDisciplina.setDisciplinaTurma(disciplinaTurma);
    }
}