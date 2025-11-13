package com.ClassExchange.usecases.manter_coordenadorCurso;

import com.ClassExchange.domain.entity.CoordenadorCurso;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CoordenadorCursoMapper {

    @Mapping(target = "professorId", source = "coordenadorCurso.professor.id")
    @Mapping(target = "professorNome", source = "coordenadorCurso.professor.nome")
    @Mapping(target = "cursoId", source = "coordenadorCurso.curso.id")
    @Mapping(target = "cursoNome", source = "coordenadorCurso.curso.nome")
    CoordenadorCursoResponse toResponse(CoordenadorCurso coordenadorCurso);
}

