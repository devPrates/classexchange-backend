package com.ClassExchange.usecases.manter_estudanteDisciplina;

import com.ClassExchange.domain.entity.*;
import org.springframework.stereotype.Component;

@Component
public class EstudanteDisciplinaMapper {

    public EstudanteDisciplinaResponse toResponse(EstudanteDisciplina estudanteDisciplina) {
        return new EstudanteDisciplinaResponse(
                estudanteDisciplina.getId(),
                estudanteDisciplina.getEstudanteCurso() != null ? estudanteDisciplina.getEstudanteCurso().getId() : null,
                estudanteDisciplina.getEstudanteCurso() != null && estudanteDisciplina.getEstudanteCurso().getCurso() != null ? estudanteDisciplina.getEstudanteCurso().getCurso().getNome() : null,
                estudanteDisciplina.getEstudanteCurso() != null && estudanteDisciplina.getEstudanteCurso().getEstudante() != null ? estudanteDisciplina.getEstudanteCurso().getEstudante().getNome() : null,
                estudanteDisciplina.getAula() != null ? estudanteDisciplina.getAula().getId() : null,
                estudanteDisciplina.getAula() != null && estudanteDisciplina.getAula().getPeriodo() != null ? estudanteDisciplina.getAula().getPeriodo().getNome() : null,
                estudanteDisciplina.getAula() != null && estudanteDisciplina.getAula().getDisciplina() != null ? estudanteDisciplina.getAula().getDisciplina().getNome() : null,
                estudanteDisciplina.getAula() != null && estudanteDisciplina.getAula().getProfessor() != null ? estudanteDisciplina.getAula().getProfessor().getNome() : null,
                estudanteDisciplina.getAula() != null && estudanteDisciplina.getAula().getLocal() != null ? estudanteDisciplina.getAula().getLocal().getNome() : null,
                estudanteDisciplina.getCreatedAt(),
                estudanteDisciplina.getUpdatedAt()
        );
    }

    public EstudanteDisciplina toEntity(EstudanteDisciplinaRequest request, EstudanteCurso estudanteCurso, Aula aula) {
        EstudanteDisciplina ed = new EstudanteDisciplina();
        ed.setEstudanteCurso(estudanteCurso);
        ed.setAula(aula);
        return ed;
    }

    public void updateEntityFromRequest(EstudanteDisciplinaRequest request, EstudanteDisciplina estudanteDisciplina, EstudanteCurso estudanteCurso, Aula aula) {
        estudanteDisciplina.setEstudanteCurso(estudanteCurso);
        estudanteDisciplina.setAula(aula);
    }
}
