package com.ClassExchange.usecases.manter_horarios;

import com.ClassExchange.domain.entity.CargaHoraria;
import com.ClassExchange.domain.entity.DisciplinaTurma;
import com.ClassExchange.domain.entity.Horario;
import org.springframework.stereotype.Component;

@Component
public class HorarioMapper {

    public HorarioResponse toResponse(Horario horario) {
        return new HorarioResponse(
                horario.getId(),
                horario.getDiaDaSemana(),
                horario.getHoraInicio(),
                horario.getHoraFim(),
                horario.getDisciplinaTurma() != null ? horario.getDisciplinaTurma().getId() : null,
                horario.getDisciplinaTurma() != null && horario.getDisciplinaTurma().getDisciplina() != null ? horario.getDisciplinaTurma().getDisciplina().getNome() : null,
                horario.getDisciplinaTurma() != null && horario.getDisciplinaTurma().getTurma() != null ? horario.getDisciplinaTurma().getTurma().getNome() : null,
                horario.getCargaHoraria() != null ? horario.getCargaHoraria().getId() : null,
                horario.getCargaHoraria() != null ? horario.getCargaHoraria().getNome() : null,
                horario.getCreatedAt(),
                horario.getUpdatedAt()
        );
    }

    public Horario toEntity(HorarioRequest request, DisciplinaTurma disciplinaTurma, CargaHoraria cargaHoraria) {
        Horario horario = new Horario();
        horario.setDiaDaSemana(request.diaDaSemana());
        horario.setHoraInicio(request.horaInicio());
        horario.setHoraFim(request.horaFim());
        horario.setDisciplinaTurma(disciplinaTurma);
        horario.setCargaHoraria(cargaHoraria);
        return horario;
    }

    public void updateEntityFromRequest(HorarioRequest request, Horario horario, DisciplinaTurma disciplinaTurma, CargaHoraria cargaHoraria) {
        horario.setDiaDaSemana(request.diaDaSemana());
        horario.setHoraInicio(request.horaInicio());
        horario.setHoraFim(request.horaFim());
        horario.setDisciplinaTurma(disciplinaTurma);
        horario.setCargaHoraria(cargaHoraria);
    }
}