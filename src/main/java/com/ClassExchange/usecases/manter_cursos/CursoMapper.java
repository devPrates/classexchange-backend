package com.ClassExchange.usecases.manter_cursos;

import com.ClassExchange.domain.entity.Curso;
import com.ClassExchange.domain.entity.Disciplina;
import com.ClassExchange.domain.entity.Turma;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CursoMapper {

    @Mapping(target = "campusId", source = "curso.campus.id")
    @Mapping(target = "campusNome", source = "curso.campus.nome")
    @Mapping(target = "disciplinas", source = "disciplinas")
    @Mapping(target = "turmas", source = "turmas")
    CursoResponse toResponse(Curso curso, List<CursoResponse.DisciplinaSimplificada> disciplinas, List<CursoResponse.TurmaSimplificada> turmas);

    @Mapping(target = "cargaHoraria", source = "cargaHoraria")
    CursoResponse.DisciplinaSimplificada toDisciplinaSimplificada(Disciplina disciplina);

    CursoResponse.TurmaSimplificada toTurmaSimplificada(Turma turma);

    List<CursoResponse.DisciplinaSimplificada> toDisciplinaSimplificadaList(List<Disciplina> disciplinas);

    List<CursoResponse.TurmaSimplificada> toTurmaSimplificadaList(List<Turma> turmas);
}