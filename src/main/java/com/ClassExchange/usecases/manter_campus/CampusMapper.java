package com.ClassExchange.usecases.manter_campus;

import com.ClassExchange.domain.entity.Campus;
import com.ClassExchange.domain.entity.Curso;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CampusMapper {

    CampusResponse toResponse(Campus campus);

    @Mapping(target = "cursos", source = "cursos")
    CampusResponse toResponseWithCursos(Campus campus, List<CampusResponse.CursoSimplificado> cursos);

    CampusResponse.CursoSimplificado toCursoSimplificado(Curso curso);

    List<CampusResponse.CursoSimplificado> toCursoSimplificadoList(List<Curso> cursos);
}