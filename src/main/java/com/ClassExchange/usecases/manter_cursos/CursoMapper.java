package com.ClassExchange.usecases.manter_cursos;

import com.ClassExchange.domain.entity.Curso;
import com.ClassExchange.domain.entity.Turma;
import com.ClassExchange.domain.entity.CoordenadorCurso;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CursoMapper {

    public CursoResponse toResponse(Curso curso, List<CursoResponse.TurmaSimplificada> turmas, List<CursoResponse.ProfessorCursoSimplificado> professoresCurso, CursoResponse.CoordenadorSimplificado coordenadorCurso, long studentsCount) {
        return new CursoResponse(
                curso.getId(),
                curso.getNome(),
                curso.getSigla(),
                curso.getSlug(),
                curso.getCampus() != null ? curso.getCampus().getId() : null,
                curso.getCampus() != null ? curso.getCampus().getNome() : null,
                turmas,
                professoresCurso,
                coordenadorCurso,
                studentsCount,
                curso.getCreatedAt(),
                curso.getUpdatedAt()
        );
    }

    public CursoResponse.TurmaSimplificada toTurmaSimplificada(Turma turma) {
        return new CursoResponse.TurmaSimplificada(
                turma.getId(),
                turma.getNome(),
                turma.getNumero()
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

    public CursoResponse.ProfessorCursoSimplificado toProfessorCursoSimplificado(com.ClassExchange.domain.entity.ProfessorCurso professorCurso) {
        return new CursoResponse.ProfessorCursoSimplificado(
                professorCurso.getId(),
                professorCurso.getUsuario() != null ? professorCurso.getUsuario().getId() : null,
                professorCurso.getUsuario() != null ? professorCurso.getUsuario().getNome() : null,
                professorCurso.getUsuario() != null ? professorCurso.getUsuario().getEmail() : null
        );
    }

    public java.util.List<CursoResponse.ProfessorCursoSimplificado> toProfessorCursoSimplificadoList(java.util.List<com.ClassExchange.domain.entity.ProfessorCurso> professoresCurso) {
        return professoresCurso.stream().map(this::toProfessorCursoSimplificado).toList();
    }

    public com.ClassExchange.usecases.manter_cursos.CursoResponse.EstudanteSimplificado toEstudanteSimplificado(com.ClassExchange.domain.entity.EstudanteCurso estudanteCurso) {
        com.ClassExchange.domain.entity.Estudante e = estudanteCurso.getEstudante();
        return new com.ClassExchange.usecases.manter_cursos.CursoResponse.EstudanteSimplificado(
                e != null ? e.getId() : null,
                e != null ? e.getNome() : null,
                e != null ? e.getEmail() : null,
                estudanteCurso.getMatricula(),
                estudanteCurso.getSituacao(),
                estudanteCurso.getVinculoCurso()
        );
    }
}
