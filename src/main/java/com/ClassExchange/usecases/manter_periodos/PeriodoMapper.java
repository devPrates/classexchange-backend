package com.ClassExchange.usecases.manter_periodos;

import com.ClassExchange.domain.entity.Disciplina;
import com.ClassExchange.domain.entity.Periodo;
import com.ClassExchange.domain.entity.Turma;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface PeriodoMapper {

    @Mapping(target = "disciplinaId", source = "periodo.disciplina.id")
    @Mapping(target = "disciplinaNome", source = "periodo.disciplina.nome")
    @Mapping(target = "turmaId", source = "periodo.turma.id")
    @Mapping(target = "turmaNome", source = "periodo.turma.nome")
    PeriodoResponse toResponse(Periodo periodo);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "slug", ignore = true)
    @Mapping(target = "nome", source = "request.nome")
    @Mapping(target = "tipoPeriodo", source = "request.tipoPeriodo")
    @Mapping(target = "numero", source = "request.numero")
    @Mapping(target = "ano", source = "request.ano")
    @Mapping(target = "inicio", source = "request.inicio")
    @Mapping(target = "fim", source = "request.fim")
    @Mapping(target = "disciplina", source = "disciplina")
    @Mapping(target = "turma", source = "turma")
    Periodo toEntity(PeriodoRequest request, Disciplina disciplina, Turma turma);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "slug", ignore = true)
    @Mapping(target = "nome", source = "request.nome")
    @Mapping(target = "tipoPeriodo", source = "request.tipoPeriodo")
    @Mapping(target = "numero", source = "request.numero")
    @Mapping(target = "ano", source = "request.ano")
    @Mapping(target = "inicio", source = "request.inicio")
    @Mapping(target = "fim", source = "request.fim")
    @Mapping(target = "disciplina", source = "disciplina")
    @Mapping(target = "turma", source = "turma")
    void updateEntityFromRequest(PeriodoRequest request, @MappingTarget Periodo periodo, Disciplina disciplina, Turma turma);
}
