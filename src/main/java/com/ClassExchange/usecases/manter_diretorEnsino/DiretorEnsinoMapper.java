package com.ClassExchange.usecases.manter_diretorEnsino;

import com.ClassExchange.domain.entity.DiretorEnsino;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DiretorEnsinoMapper {

    @Mapping(target = "professorId", source = "diretorEnsino.professor.id")
    @Mapping(target = "professorNome", source = "diretorEnsino.professor.nome")
    @Mapping(target = "campusId", source = "diretorEnsino.campus.id")
    @Mapping(target = "campusNome", source = "diretorEnsino.campus.nome")
    DiretorEnsinoResponse toResponse(DiretorEnsino diretorEnsino);
}

