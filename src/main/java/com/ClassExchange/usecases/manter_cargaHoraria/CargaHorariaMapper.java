package com.ClassExchange.usecases.manter_cargaHoraria;

import com.ClassExchange.domain.entity.CargaHoraria;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CargaHorariaMapper {
    CargaHorariaResponse toResponse(CargaHoraria cargaHoraria);
}

