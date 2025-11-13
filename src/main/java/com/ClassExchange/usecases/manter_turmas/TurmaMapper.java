package com.ClassExchange.usecases.manter_turmas;

import com.ClassExchange.domain.entity.Turma;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TurmaMapper {

    @Mapping(target = "cursoId", source = "turma.curso.id")
    @Mapping(target = "cursoNome", source = "turma.curso.nome")
    TurmaResponse toResponse(Turma turma);
}
