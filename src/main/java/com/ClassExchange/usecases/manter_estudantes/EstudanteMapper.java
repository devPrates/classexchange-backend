package com.ClassExchange.usecases.manter_estudantes;

import com.ClassExchange.domain.entity.Estudante;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EstudanteMapper {
    EstudanteResponse toResponse(Estudante estudante);
}

