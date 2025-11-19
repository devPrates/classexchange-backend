package com.ClassExchange.usecases.manter_estudanteDisciplina;

import com.ClassExchange.domain.entity.DisciplinaTurma;
import com.ClassExchange.domain.entity.Estudante;
import com.ClassExchange.domain.entity.EstudanteDisciplina;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface EstudanteDisciplinaMapper {

    @Mapping(target = "estudanteId", source = "estudanteDisciplina.estudante.id")
    @Mapping(target = "estudanteNome", source = "estudanteDisciplina.estudante.nome")
    @Mapping(target = "disciplinaTurmaId", source = "estudanteDisciplina.disciplinaTurma.id")
    @Mapping(target = "disciplinaNome", source = "estudanteDisciplina.disciplinaTurma.disciplina.nome")
    @Mapping(target = "turmaNome", source = "estudanteDisciplina.disciplinaTurma.turma.nome")
    EstudanteDisciplinaResponse toResponse(EstudanteDisciplina estudanteDisciplina);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "estudante", source = "estudante")
    @Mapping(target = "disciplinaTurma", source = "disciplinaTurma")
    EstudanteDisciplina toEntity(EstudanteDisciplinaRequest request, Estudante estudante, DisciplinaTurma disciplinaTurma);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "estudante", source = "estudante")
    @Mapping(target = "disciplinaTurma", source = "disciplinaTurma")
    void updateEntityFromRequest(EstudanteDisciplinaRequest request, @MappingTarget EstudanteDisciplina estudanteDisciplina, Estudante estudante, DisciplinaTurma disciplinaTurma);
}