package com.ClassExchange.usecases.manter_disciplinaTurma;

import com.ClassExchange.domain.entity.Disciplina;
import com.ClassExchange.domain.entity.DisciplinaTurma;
import com.ClassExchange.domain.entity.Local;
import com.ClassExchange.domain.entity.Turma;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface DisciplinaTurmaMapper {

    @Mapping(target = "disciplinaId", source = "disciplinaTurma.disciplina.id")
    @Mapping(target = "disciplinaNome", source = "disciplinaTurma.disciplina.nome")
    @Mapping(target = "turmaId", source = "disciplinaTurma.turma.id")
    @Mapping(target = "turmaNome", source = "disciplinaTurma.turma.nome")
    @Mapping(target = "localId", source = "disciplinaTurma.local.id")
    @Mapping(target = "localNome", source = "disciplinaTurma.local.nome")
    DisciplinaTurmaResponse toResponse(DisciplinaTurma disciplinaTurma);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "disciplina", source = "disciplina")
    @Mapping(target = "turma", source = "turma")
    @Mapping(target = "local", source = "local")
    DisciplinaTurma toEntity(DisciplinaTurmaRequest request, Disciplina disciplina, Turma turma, Local local);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "disciplina", source = "disciplina")
    @Mapping(target = "turma", source = "turma")
    @Mapping(target = "local", source = "local")
    void updateEntityFromRequest(DisciplinaTurmaRequest request, @MappingTarget DisciplinaTurma disciplinaTurma, Disciplina disciplina, Turma turma, Local local);
}