package com.ClassExchange.usecases.manter_cargaHoraria;

import com.ClassExchange.domain.entity.CargaHoraria;
import org.springframework.stereotype.Component;

@Component
public class CargaHorariaMapper {
    public CargaHorariaResponse toResponse(CargaHoraria cargaHoraria) {
        return new CargaHorariaResponse(
                cargaHoraria.getId(),
                cargaHoraria.getNome(),
                cargaHoraria.getDuracao(),
                cargaHoraria.getMedidaTempo(),
                cargaHoraria.getCreatedAt(),
                cargaHoraria.getUpdatedAt()
        );
    }
}

