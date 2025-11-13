package com.ClassExchange.usecases.manter_periodos;

import com.ClassExchange.domain.entity.Periodo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PeriodoMapper {

    @Mapping(target = "disciplinaId", source = "periodo.disciplina.id")
    @Mapping(target = "disciplinaNome", source = "periodo.disciplina.nome")
    @Mapping(target = "turmaId", source = "periodo.turma.id")
    @Mapping(target = "turmaNome", source = "periodo.turma.nome")
    PeriodoResponse toResponse(Periodo periodo);
}
