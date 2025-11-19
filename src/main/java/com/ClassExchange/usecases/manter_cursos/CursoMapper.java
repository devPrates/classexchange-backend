package com.ClassExchange.usecases.manter_cursos;

import com.ClassExchange.domain.entity.Curso;
import com.ClassExchange.domain.entity.Turma;
import com.ClassExchange.domain.entity.CoordenadorCurso;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CursoMapper {

    public CursoResponse toResponse(Curso curso, List<CursoResponse.TurmaSimplificada> turmas, CursoResponse.CoordenadorSimplificado coordenadorCurso) {
        return new CursoResponse(
                curso.getId(),
                curso.getNome(),
                curso.getSigla(),
                curso.getSlug(),
                curso.getCampus() != null ? curso.getCampus().getId() : null,
                curso.getCampus() != null ? curso.getCampus().getNome() : null,
                turmas,
                coordenadorCurso,
                curso.getCreatedAt(),
                curso.getUpdatedAt()
        );
    }

    public CursoResponse.TurmaSimplificada toTurmaSimplificada(Turma turma) {
        return new CursoResponse.TurmaSimplificada(
                turma.getId(),
                turma.getNome()
        );
    }

    public java.util.List<CursoResponse.TurmaSimplificada> toTurmaSimplificadaList(List<Turma> turmas) {
        return turmas.stream().map(this::toTurmaSimplificada).toList();
    }

    public CursoResponse.CoordenadorSimplificado toCoordenadorSimplificado(CoordenadorCurso coordenadorCurso) {
        return new CursoResponse.CoordenadorSimplificado(
                coordenadorCurso.getId(),
                coordenadorCurso.getUsuario() != null ? coordenadorCurso.getUsuario().getId() : null,
                coordenadorCurso.getUsuario() != null ? coordenadorCurso.getUsuario().getNome() : null,
                coordenadorCurso.getInicio(),
                coordenadorCurso.getFim()
        );
    }
}