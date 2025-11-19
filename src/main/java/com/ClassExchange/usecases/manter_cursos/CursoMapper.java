package com.ClassExchange.usecases.manter_cursos;

import com.ClassExchange.domain.entity.Curso;
import com.ClassExchange.domain.entity.Turma;
import com.ClassExchange.domain.entity.CoordenadorCurso;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CursoMapper {

    @Mapping(target = "id", source = "curso.id")
    @Mapping(target = "nome", source = "curso.nome")
    @Mapping(target = "sigla", source = "curso.sigla")
    @Mapping(target = "slug", source = "curso.slug")
    @Mapping(target = "campusId", source = "curso.campus.id")
    @Mapping(target = "campusNome", source = "curso.campus.nome")
    @Mapping(target = "turmas", source = "turmas")
    @Mapping(target = "coordenadorCurso", source = "coordenadorCurso")
    CursoResponse toResponse(Curso curso, List<CursoResponse.TurmaSimplificada> turmas, CursoResponse.CoordenadorSimplificado coordenadorCurso);

    CursoResponse.TurmaSimplificada toTurmaSimplificada(Turma turma);

    List<CursoResponse.TurmaSimplificada> toTurmaSimplificadaList(List<Turma> turmas);

    @Mapping(target = "usuarioId", source = "usuario.id")
    @Mapping(target = "usuarioNome", source = "usuario.nome")
    CursoResponse.CoordenadorSimplificado toCoordenadorSimplificado(CoordenadorCurso coordenadorCurso);
}