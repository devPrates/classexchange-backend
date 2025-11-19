package com.ClassExchange.usecases.manter_horarios;

import com.ClassExchange.domain.entity.CargaHoraria;
import com.ClassExchange.domain.entity.DisciplinaTurma;
import com.ClassExchange.domain.entity.Horario;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface HorarioMapper {


    @Mapping(target = "disciplinaTurmaId", source = "horario.disciplinaTurma.id")
    @Mapping(target = "disciplinaNome", source = "horario.disciplinaTurma.disciplina.nome")
    @Mapping(target = "turmaNome", source = "horario.disciplinaTurma.turma.nome")
    @Mapping(target = "cargaHorariaId", source = "horario.cargaHoraria.id")
    @Mapping(target = "cargaHorariaNome", source = "horario.cargaHoraria.nome")
    HorarioResponse toResponse(Horario horario);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "diaDaSemana", source = "request.diaDaSemana")
    @Mapping(target = "horaInicio", source = "request.horaInicio")
    @Mapping(target = "horaFim", source = "request.horaFim")
    @Mapping(target = "disciplinaTurma", source = "disciplinaTurma")
    @Mapping(target = "cargaHoraria", source = "cargaHoraria")
    Horario toEntity(HorarioRequest request, DisciplinaTurma disciplinaTurma, CargaHoraria cargaHoraria);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "diaDaSemana", source = "request.diaDaSemana")
    @Mapping(target = "horaInicio", source = "request.horaInicio")
    @Mapping(target = "horaFim", source = "request.horaFim")
    @Mapping(target = "disciplinaTurma", source = "disciplinaTurma")
    @Mapping(target = "cargaHoraria", source = "cargaHoraria")
    void updateEntityFromRequest(HorarioRequest request, @MappingTarget Horario horario, DisciplinaTurma disciplinaTurma, CargaHoraria cargaHoraria);
}