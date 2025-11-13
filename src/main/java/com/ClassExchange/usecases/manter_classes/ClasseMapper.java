package com.ClassExchange.usecases.manter_classes;

import com.ClassExchange.domain.entity.Classe;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ClasseMapper {

    @Mapping(target = "disciplinaId", source = "classe.disciplina.id")
    @Mapping(target = "disciplinaNome", source = "classe.disciplina.nome")
    @Mapping(target = "turmaId", source = "classe.turma.id")
    @Mapping(target = "turmaNome", source = "classe.turma.nome")
    @Mapping(target = "cargaHorariaId", source = "classe.cargaHoraria.id")
    @Mapping(target = "cargaHorariaNome", source = "classe.cargaHoraria.nome")
    @Mapping(target = "localId", source = "classe.local.id")
    @Mapping(target = "localNome", source = "classe.local.nome")
    ClasseResponse toResponse(Classe classe);
}

