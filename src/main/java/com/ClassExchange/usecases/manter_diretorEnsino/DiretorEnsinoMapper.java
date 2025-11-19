package com.ClassExchange.usecases.manter_diretorEnsino;

import com.ClassExchange.domain.entity.DiretorEnsino;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DiretorEnsinoMapper {

    @Mapping(target = "usuarioId", source = "diretorEnsino.usuario.id")
    @Mapping(target = "usuarioNome", source = "diretorEnsino.usuario.nome")
    @Mapping(target = "campusId", source = "diretorEnsino.campus.id")
    @Mapping(target = "campusNome", source = "diretorEnsino.campus.nome")
    DiretorEnsinoResponse toResponse(DiretorEnsino diretorEnsino);
}

