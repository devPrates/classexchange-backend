package com.ClassExchange.usecases.manter_horarios;

import com.ClassExchange.domain.entity.Horario;
import com.ClassExchange.domain.entity.Aula;
import org.springframework.stereotype.Component;

@Component
public class HorarioMapper {

    public HorarioResponse toResponse(Horario horario) {
        return new HorarioResponse(
                horario.getId(),
                horario.getDiaDaSemana(),
                horario.getHoraInicio() != null ? horario.getHoraInicio().toString() : null,
                horario.getHoraFim() != null ? horario.getHoraFim().toString() : null,
                horario.getAula() != null ? horario.getAula().getId() : null,
                horario.getAula() != null && horario.getAula().getPeriodo() != null ? horario.getAula().getPeriodo().getId() : null,
                horario.getAula() != null && horario.getAula().getPeriodo() != null ? horario.getAula().getPeriodo().getNome() : null,
                horario.getAula() != null && horario.getAula().getDisciplina() != null ? horario.getAula().getDisciplina().getId() : null,
                horario.getAula() != null && horario.getAula().getDisciplina() != null ? horario.getAula().getDisciplina().getNome() : null,
                horario.getAula() != null && horario.getAula().getProfessor() != null ? horario.getAula().getProfessor().getId() : null,
                horario.getAula() != null && horario.getAula().getProfessor() != null ? horario.getAula().getProfessor().getNome() : null,
                horario.getAula() != null && horario.getAula().getLocal() != null ? horario.getAula().getLocal().getId() : null,
                horario.getAula() != null && horario.getAula().getLocal() != null ? horario.getAula().getLocal().getNome() : null,
                horario.getCreatedAt(),
                horario.getUpdatedAt()
        );
    }

    public Horario toEntity(HorarioRequest request, Aula aula) {
        Horario horario = new Horario();
        horario.setDiaDaSemana(request.diaDaSemana());
        horario.setHoraInicio(java.time.LocalTime.parse(request.horaInicio()));
        horario.setHoraFim(java.time.LocalTime.parse(request.horaFim()));
        horario.setAula(aula);
        return horario;
    }

    public void updateEntityFromRequest(HorarioRequest request, Horario horario, Aula aula) {
        horario.setDiaDaSemana(request.diaDaSemana());
        horario.setHoraInicio(java.time.LocalTime.parse(request.horaInicio()));
        horario.setHoraFim(java.time.LocalTime.parse(request.horaFim()));
        horario.setAula(aula);
    }
}
