package com.ClassExchange.usecases.manter_estudanteCurso;

import com.ClassExchange.domain.entity.EstudanteCurso;
import org.springframework.stereotype.Component;

@Component
public class EstudanteCursoMapper {
    public EstudanteCursoResponse toResponse(EstudanteCurso ec) {
        return new EstudanteCursoResponse(
                ec.getId(),
                ec.getMatricula(),
                ec.getVinculoCurso(),
                ec.getSituacao(),
                ec.getEstudante() != null ? ec.getEstudante().getId() : null,
                ec.getEstudante() != null ? ec.getEstudante().getNome() : null,
                ec.getCurso() != null ? ec.getCurso().getId() : null,
                ec.getCurso() != null ? ec.getCurso().getNome() : null,
                ec.getCreatedAt(),
                ec.getUpdatedAt()
        );
    }
}