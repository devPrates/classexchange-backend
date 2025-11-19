package com.ClassExchange.usecases.manter_professorDisciplina;

import com.ClassExchange.domain.entity.DisciplinaTurma;
import com.ClassExchange.domain.entity.ProfessorDisciplina;
import com.ClassExchange.domain.entity.Usuario;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface ProfessorDisciplinaMapper {

    @Mapping(target = "usuarioId", source = "professorDisciplina.usuario.id")
    @Mapping(target = "usuarioNome", source = "professorDisciplina.usuario.nome")
    @Mapping(target = "disciplinaTurmaId", source = "professorDisciplina.disciplinaTurma.id")
    @Mapping(target = "disciplinaNome", source = "professorDisciplina.disciplinaTurma.disciplina.nome")
    @Mapping(target = "turmaNome", source = "professorDisciplina.disciplinaTurma.turma.nome")
    ProfessorDisciplinaResponse toResponse(ProfessorDisciplina professorDisciplina);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "inicio", source = "request.inicio")
    @Mapping(target = "fim", source = "request.fim")
    @Mapping(target = "usuario", source = "usuario")
    @Mapping(target = "disciplinaTurma", source = "disciplinaTurma")
    ProfessorDisciplina toEntity(ProfessorDisciplinaRequest request, Usuario usuario, DisciplinaTurma disciplinaTurma);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "inicio", source = "request.inicio")
    @Mapping(target = "fim", source = "request.fim")
    @Mapping(target = "usuario", source = "usuario")
    @Mapping(target = "disciplinaTurma", source = "disciplinaTurma")
    void updateEntityFromRequest(ProfessorDisciplinaRequest request, @MappingTarget ProfessorDisciplina professorDisciplina, Usuario usuario, DisciplinaTurma disciplinaTurma);
}