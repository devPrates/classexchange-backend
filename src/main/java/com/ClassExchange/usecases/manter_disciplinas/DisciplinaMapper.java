package com.ClassExchange.usecases.manter_disciplinas;

import com.ClassExchange.domain.entity.Curso;
import com.ClassExchange.domain.entity.Disciplina;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface DisciplinaMapper {

    @Mapping(target = "cursoId", source = "disciplina.curso.id")
    @Mapping(target = "cursoNome", source = "disciplina.curso.nome")
    DisciplinaResponse toResponse(Disciplina disciplina);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "periodos", ignore = true)
    @Mapping(target = "slug", ignore = true)
    @Mapping(target = "nome", source = "request.nome")
    @Mapping(target = "periodo", source = "request.periodo")
    @Mapping(target = "cargaHoraria", source = "request.cargaHoraria")
    @Mapping(target = "ementa", source = "request.ementa")
    @Mapping(target = "curso", source = "curso")
    Disciplina toEntity(DisciplinaRequest request, Curso curso);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "periodos", ignore = true)
    @Mapping(target = "slug", ignore = true)
    @Mapping(target = "nome", source = "request.nome")
    @Mapping(target = "periodo", source = "request.periodo")
    @Mapping(target = "cargaHoraria", source = "request.cargaHoraria")
    @Mapping(target = "ementa", source = "request.ementa")
    @Mapping(target = "curso", source = "curso")
    void updateEntityFromRequest(DisciplinaRequest request, @MappingTarget Disciplina disciplina, Curso curso);
}
