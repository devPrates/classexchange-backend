package com.ClassExchange.usecases.manter_professorClasse;

import com.ClassExchange.domain.entity.ProfessorClasse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProfessorClasseMapper {

    @Mapping(target = "professorId", source = "professorClasse.professor.id")
    @Mapping(target = "professorNome", source = "professorClasse.professor.nome")
    @Mapping(target = "classeId", source = "professorClasse.classe.id")
    @Mapping(target = "classeNome", source = "professorClasse.classe.turma.nome")
    ProfessorClasseResponse toResponse(ProfessorClasse professorClasse);
}

