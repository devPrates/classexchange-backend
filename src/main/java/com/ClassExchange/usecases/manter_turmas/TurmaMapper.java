package com.ClassExchange.usecases.manter_turmas;

import com.ClassExchange.domain.entity.Curso;
import com.ClassExchange.domain.entity.Turma;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface TurmaMapper {

    @Mapping(target = "cursoId", source = "turma.curso.id")
    @Mapping(target = "cursoNome", source = "turma.curso.nome")
    TurmaResponse toResponse(Turma turma);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "periodos", ignore = true)
    @Mapping(target = "slug", ignore = true)
    @Mapping(target = "nome", source = "request.nome")
    @Mapping(target = "numero", source = "request.numero")
    @Mapping(target = "curso", source = "curso")
    Turma toEntity(TurmaRequest request, Curso curso);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "periodos", ignore = true)
    @Mapping(target = "slug", ignore = true)
    @Mapping(target = "nome", source = "request.nome")
    @Mapping(target = "numero", source = "request.numero")
    @Mapping(target = "curso", source = "curso")
    void updateEntityFromRequest(TurmaRequest request, @MappingTarget Turma turma, Curso curso);
}
