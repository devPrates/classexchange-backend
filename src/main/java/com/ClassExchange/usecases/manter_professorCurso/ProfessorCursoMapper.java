package com.ClassExchange.usecases.manter_professorCurso;

import com.ClassExchange.domain.entity.Curso;
import com.ClassExchange.domain.entity.ProfessorCurso;
import com.ClassExchange.domain.entity.Usuario;
import org.springframework.stereotype.Component;

@Component
public class ProfessorCursoMapper {

    public ProfessorCursoResponse toResponse(ProfessorCurso professorCurso) {
        return new ProfessorCursoResponse(
                professorCurso.getId(),
                professorCurso.getUsuario() != null ? professorCurso.getUsuario().getId() : null,
                professorCurso.getUsuario() != null ? professorCurso.getUsuario().getNome() : null,
                professorCurso.getCurso() != null ? professorCurso.getCurso().getId() : null,
                professorCurso.getCurso() != null ? professorCurso.getCurso().getNome() : null,
                professorCurso.getCreatedAt(),
                professorCurso.getUpdatedAt()
        );
    }

    public ProfessorCurso toEntity(ProfessorCursoRequest request, Usuario usuario, Curso curso) {
        ProfessorCurso pc = new ProfessorCurso();
        pc.setUsuario(usuario);
        pc.setCurso(curso);
        return pc;
    }

    public void updateEntityFromRequest(ProfessorCursoRequest request, ProfessorCurso professorCurso, Usuario usuario, Curso curso) {
        professorCurso.setUsuario(usuario);
        professorCurso.setCurso(curso);
    }
}