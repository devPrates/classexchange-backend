package com.ClassExchange.usecases.manter_locais;

import com.ClassExchange.domain.entity.Local;
import org.springframework.stereotype.Component;

@Component
public class LocalMapper {
    public LocalResponse toResponse(Local local) {
        return new LocalResponse(
                local.getId(),
                local.getNome(),
                local.getCampus() != null ? local.getCampus().getId() : null,
                local.getCampus() != null ? local.getCampus().getNome() : null,
                local.getCapacidade(),
                local.getBloco(),
                local.getAndar(),
                local.getTipo(),
                local.getCreatedAt(),
                local.getUpdatedAt()
        );
    }
}
