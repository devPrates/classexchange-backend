package com.ClassExchange.usecases.manter_usuarios;

import com.ClassExchange.domain.entity.Usuario;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface UsuarioMapper {

    @Mapping(target = "campusId", source = "usuario.campus.id")
    @Mapping(target = "campusNome", source = "usuario.campus.nome")
    UsuarioResponse toResponse(Usuario usuario);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "campus", ignore = true)
    @Mapping(target = "professorCursos", ignore = true)
    @Mapping(target = "professorDisciplinas", ignore = true)
    Usuario toEntity(UsuarioRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "campus", ignore = true)
    @Mapping(target = "professorCursos", ignore = true)
    @Mapping(target = "professorDisciplinas", ignore = true)
    void updateEntityFromRequest(UsuarioRequest request, @MappingTarget Usuario usuario);
}