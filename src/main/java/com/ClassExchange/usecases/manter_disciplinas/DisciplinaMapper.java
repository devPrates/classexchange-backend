package com.ClassExchange.usecases.manter_disciplinas;

import com.ClassExchange.domain.entity.Disciplina;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DisciplinaMapper {

    @Mapping(target = "cursoId", source = "disciplina.curso.id")
    @Mapping(target = "cursoNome", source = "disciplina.curso.nome")
    DisciplinaResponse toResponse(Disciplina disciplina);
}

