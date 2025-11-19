package com.ClassExchange.usecases.manter_coordenadorCurso;

import com.ClassExchange.domain.entity.CoordenadorCurso;
import org.springframework.stereotype.Component;

@Component
public class CoordenadorCursoMapper {

    public CoordenadorCursoResponse toResponse(CoordenadorCurso coordenadorCurso) {
        return new CoordenadorCursoResponse(
                coordenadorCurso.getId(),
                coordenadorCurso.getInicio(),
                coordenadorCurso.getFim(),
                coordenadorCurso.getUsuario() != null ? coordenadorCurso.getUsuario().getId() : null,
                coordenadorCurso.getUsuario() != null ? coordenadorCurso.getUsuario().getNome() : null,
                coordenadorCurso.getCurso() != null ? coordenadorCurso.getCurso().getId() : null,
                coordenadorCurso.getCurso() != null ? coordenadorCurso.getCurso().getNome() : null,
                coordenadorCurso.getCreatedAt(),
                coordenadorCurso.getUpdatedAt()
        );
    }
}

