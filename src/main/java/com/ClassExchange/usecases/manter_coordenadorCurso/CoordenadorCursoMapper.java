package com.ClassExchange.usecases.manter_coordenadorCurso;

import com.ClassExchange.domain.entity.CoordenadorCurso;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CoordenadorCursoMapper {

    @Mapping(target = "usuarioId", source = "coordenadorCurso.usuario.id")
    @Mapping(target = "usuarioNome", source = "coordenadorCurso.usuario.nome")
    @Mapping(target = "cursoId", source = "coordenadorCurso.curso.id")
    @Mapping(target = "cursoNome", source = "coordenadorCurso.curso.nome")
    CoordenadorCursoResponse toResponse(CoordenadorCurso coordenadorCurso);
}

