package com.ClassExchange.usecases.manter_locais;

import com.ClassExchange.domain.entity.Local;
import org.springframework.stereotype.Component;

@Component
public class LocalMapper {
    public LocalResponse toResponse(Local local) {
        return new LocalResponse(
                local.getId(),
                local.getNome(),
                local.getCreatedAt(),
                local.getUpdatedAt()
        );
    }
}

