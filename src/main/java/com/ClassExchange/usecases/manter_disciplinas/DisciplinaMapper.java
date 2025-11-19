package com.ClassExchange.usecases.manter_disciplinas;

import com.ClassExchange.domain.entity.Curso;
import com.ClassExchange.domain.entity.Disciplina;
import com.ClassExchange.domain.entity.Periodo;
import org.springframework.stereotype.Component;

@Component
public class DisciplinaMapper {

    public DisciplinaResponse toResponse(Disciplina disciplina) {
        return new DisciplinaResponse(
                disciplina.getId(),
                disciplina.getNome(),
                disciplina.getSlug(),
                disciplina.getPeriodo() != null ? disciplina.getPeriodo().getNumero() : 0,
                disciplina.getPeriodo() != null ? disciplina.getPeriodo().getId() : null,
                disciplina.getCargaHoraria(),
                disciplina.getEmenta(),
                disciplina.getCurso() != null ? disciplina.getCurso().getId() : null,
                disciplina.getCurso() != null ? disciplina.getCurso().getNome() : null,
                disciplina.getCreatedAt(),
                disciplina.getUpdatedAt()
        );
    }

    public Disciplina toEntity(DisciplinaRequest request, Curso curso, Periodo periodo) {
        Disciplina disciplina = new Disciplina();
        disciplina.setNome(request.nome());
        disciplina.setCargaHoraria(request.cargaHoraria());
        disciplina.setEmenta(request.ementa());
        disciplina.setCurso(curso);
        disciplina.setPeriodo(periodo);
        return disciplina;
    }

    public void updateEntityFromRequest(DisciplinaRequest request, Disciplina disciplina, Curso curso, Periodo periodo) {
        disciplina.setNome(request.nome());
        disciplina.setCargaHoraria(request.cargaHoraria());
        disciplina.setEmenta(request.ementa());
        disciplina.setCurso(curso);
        disciplina.setPeriodo(periodo);
    }
}
