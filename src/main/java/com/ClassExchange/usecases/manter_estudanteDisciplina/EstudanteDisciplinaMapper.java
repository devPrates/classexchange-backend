package com.ClassExchange.usecases.manter_estudanteDisciplina;

import com.ClassExchange.domain.entity.DisciplinaTurma;
import com.ClassExchange.domain.entity.Estudante;
import com.ClassExchange.domain.entity.EstudanteDisciplina;
import org.springframework.stereotype.Component;

@Component
public class EstudanteDisciplinaMapper {

    public EstudanteDisciplinaResponse toResponse(EstudanteDisciplina estudanteDisciplina) {
        return new EstudanteDisciplinaResponse(
                estudanteDisciplina.getId(),
                estudanteDisciplina.getMatricula(),
                estudanteDisciplina.getVinculoCurso(),
                estudanteDisciplina.getSituacao(),
                estudanteDisciplina.getEstudante() != null ? estudanteDisciplina.getEstudante().getId() : null,
                estudanteDisciplina.getEstudante() != null ? estudanteDisciplina.getEstudante().getNome() : null,
                estudanteDisciplina.getDisciplinaTurma() != null ? estudanteDisciplina.getDisciplinaTurma().getId() : null,
                estudanteDisciplina.getDisciplinaTurma() != null && estudanteDisciplina.getDisciplinaTurma().getDisciplina() != null ? estudanteDisciplina.getDisciplinaTurma().getDisciplina().getNome() : null,
                estudanteDisciplina.getDisciplinaTurma() != null && estudanteDisciplina.getDisciplinaTurma().getTurma() != null ? estudanteDisciplina.getDisciplinaTurma().getTurma().getNome() : null,
                estudanteDisciplina.getCreatedAt(),
                estudanteDisciplina.getUpdatedAt()
        );
    }

    public EstudanteDisciplina toEntity(EstudanteDisciplinaRequest request, Estudante estudante, DisciplinaTurma disciplinaTurma) {
        EstudanteDisciplina ed = new EstudanteDisciplina();
        ed.setMatricula(request.matricula());
        ed.setVinculoCurso(request.vinculoCurso());
        ed.setSituacao(request.situacao());
        ed.setEstudante(estudante);
        ed.setDisciplinaTurma(disciplinaTurma);
        return ed;
    }

    public void updateEntityFromRequest(EstudanteDisciplinaRequest request, EstudanteDisciplina estudanteDisciplina, Estudante estudante, DisciplinaTurma disciplinaTurma) {
        estudanteDisciplina.setMatricula(request.matricula());
        estudanteDisciplina.setVinculoCurso(request.vinculoCurso());
        estudanteDisciplina.setSituacao(request.situacao());
        estudanteDisciplina.setEstudante(estudante);
        estudanteDisciplina.setDisciplinaTurma(disciplinaTurma);
    }
}