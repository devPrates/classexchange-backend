package com.ClassExchange.usecases.manter_professorCurso;

import com.ClassExchange.domain.entity.Curso;
import com.ClassExchange.domain.entity.ProfessorCurso;
import com.ClassExchange.domain.entity.Usuario;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface ProfessorCursoMapper {

    @Mapping(target = "usuarioId", source = "professorCurso.usuario.id")
    @Mapping(target = "usuarioNome", source = "professorCurso.usuario.nome")
    @Mapping(target = "cursoId", source = "professorCurso.curso.id")
    @Mapping(target = "cursoNome", source = "professorCurso.curso.nome")
    ProfessorCursoResponse toResponse(ProfessorCurso professorCurso);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "usuario", source = "usuario")
    @Mapping(target = "curso", source = "curso")
    ProfessorCurso toEntity(ProfessorCursoRequest request, Usuario usuario, Curso curso);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "usuario", source = "usuario")
    @Mapping(target = "curso", source = "curso")
    void updateEntityFromRequest(ProfessorCursoRequest request, @MappingTarget ProfessorCurso professorCurso, Usuario usuario, Curso curso);
}