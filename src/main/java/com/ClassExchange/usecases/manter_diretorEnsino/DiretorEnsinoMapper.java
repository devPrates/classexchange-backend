package com.ClassExchange.usecases.manter_diretorEnsino;

import com.ClassExchange.domain.entity.DiretorEnsino;
import org.springframework.stereotype.Component;

@Component
public class DiretorEnsinoMapper {

    public DiretorEnsinoResponse toResponse(DiretorEnsino diretorEnsino) {
        return new DiretorEnsinoResponse(
                diretorEnsino.getId(),
                diretorEnsino.getInicio(),
                diretorEnsino.getFim(),
                diretorEnsino.getUsuario() != null ? diretorEnsino.getUsuario().getId() : null,
                diretorEnsino.getUsuario() != null ? diretorEnsino.getUsuario().getNome() : null,
                diretorEnsino.getCampus() != null ? diretorEnsino.getCampus().getId() : null,
                diretorEnsino.getCampus() != null ? diretorEnsino.getCampus().getNome() : null,
                diretorEnsino.getCreatedAt(),
                diretorEnsino.getUpdatedAt()
        );
    }
}

