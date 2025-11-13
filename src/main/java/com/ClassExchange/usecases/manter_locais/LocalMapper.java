package com.ClassExchange.usecases.manter_locais;

import com.ClassExchange.domain.entity.Local;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LocalMapper {
    LocalResponse toResponse(Local local);
}

