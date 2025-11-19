package com.ClassExchange.usecases.manter_turmas;

import com.ClassExchange.domain.entity.Curso;
import com.ClassExchange.domain.entity.Turma;
import org.springframework.stereotype.Component;

@Component
public class TurmaMapper {

    public TurmaResponse toResponse(Turma turma) {
        return new TurmaResponse(
                turma.getId(),
                turma.getNome(),
                turma.getSlug(),
                turma.getNumero(),
                turma.getCurso() != null ? turma.getCurso().getId() : null,
                turma.getCurso() != null ? turma.getCurso().getNome() : null,
                turma.getCreatedAt(),
                turma.getUpdatedAt()
        );
    }

    public Turma toEntity(TurmaRequest request, Curso curso) {
        Turma turma = new Turma();
        turma.setNome(request.nome());
        turma.setNumero(request.numero());
        turma.setCurso(curso);
        return turma;
    }

    public void updateEntityFromRequest(TurmaRequest request, Turma turma, Curso curso) {
        turma.setNome(request.nome());
        turma.setNumero(request.numero());
        turma.setCurso(curso);
    }
}
