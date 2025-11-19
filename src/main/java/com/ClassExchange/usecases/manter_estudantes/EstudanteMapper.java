package com.ClassExchange.usecases.manter_estudantes;

import com.ClassExchange.domain.entity.Estudante;
import org.springframework.stereotype.Component;

@Component
public class EstudanteMapper {
    public EstudanteResponse toResponse(Estudante estudante) {
        return new EstudanteResponse(
                estudante.getId(),
                estudante.getNome(),
                estudante.getEmail(),
                estudante.getCreatedAt(),
                estudante.getUpdatedAt()
        );
    }
}

