package com.ClassExchange.usecases.manter_campus;

import com.ClassExchange.domain.entity.Campus;
import com.ClassExchange.domain.entity.Curso;
import org.mapstruct.Mapper;
import org.mapstruct.Builder;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.lang.NonNull;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface CampusMapper {
    @Mapping(target = "id", source = "campus.id")
    @Mapping(target = "nome", source = "campus.nome")
    @Mapping(target = "slug", source = "campus.slug")
    
    @NonNull CampusResponse toResponse(Campus campus);

    @Mapping(target = "cursos", source = "cursos")
    @NonNull CampusResponse toResponseWithCursos(Campus campus, List<CampusResponse.CursoSimplificado> cursos);

    @NonNull CampusResponse.CursoSimplificado toCursoSimplificado(Curso curso);

    List<CampusResponse.CursoSimplificado> toCursoSimplificadoList(List<Curso> cursos);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "slug", expression = "java(com.ClassExchange.utils.SlugUtils.generateSlug(request.nome()))")
    @Mapping(target = "cursos", ignore = true)
    @Mapping(target = "diretorEnsino", ignore = true)
    @NonNull Campus toEntity(CampusRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "cursos", ignore = true)
    @Mapping(target = "diretorEnsino", ignore = true)
    @Mapping(target = "slug", expression = "java(com.ClassExchange.utils.SlugUtils.generateSlug(request.nome()))")
    void updateEntityFromRequest(CampusRequest request, @MappingTarget Campus campus);
}