package com.ClassExchange.usecases.manter_periodos;

import com.ClassExchange.domain.entity.Periodo;
import com.ClassExchange.domain.entity.Turma;
import org.springframework.stereotype.Component;

@Component
public class PeriodoMapper {

    public PeriodoResponse toResponse(Periodo periodo) {
        return new PeriodoResponse(
                periodo.getId(),
                periodo.getNome(),
                periodo.getSlug(),
                periodo.getTipoPeriodo(),
                periodo.getNumero(),
                periodo.getAno(),
                periodo.getInicio(),
                periodo.getFim(),
                periodo.getTurma() != null ? periodo.getTurma().getId() : null,
                periodo.getTurma() != null ? periodo.getTurma().getNome() : null,
                periodo.getCreatedAt(),
                periodo.getUpdatedAt()
        );
    }

    public Periodo toEntity(PeriodoRequest request, Turma turma) {
        Periodo periodo = new Periodo();
        periodo.setNome(request.nome());
        periodo.setTipoPeriodo(request.tipoPeriodo());
        periodo.setNumero(request.numero());
        periodo.setAno(request.ano());
        periodo.setInicio(request.inicio());
        periodo.setFim(request.fim());
        periodo.setTurma(turma);
        return periodo;
    }

    public void updateEntityFromRequest(PeriodoRequest request, Periodo periodo, Turma turma) {
        periodo.setNome(request.nome());
        periodo.setTipoPeriodo(request.tipoPeriodo());
        periodo.setNumero(request.numero());
        periodo.setAno(request.ano());
        periodo.setInicio(request.inicio());
        periodo.setFim(request.fim());
        periodo.setTurma(turma);
    }
}
