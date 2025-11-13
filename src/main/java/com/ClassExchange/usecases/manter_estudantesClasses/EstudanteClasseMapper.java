package com.ClassExchange.usecases.manter_estudantesClasses;

import com.ClassExchange.domain.entity.EstudanteClasse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EstudanteClasseMapper {

    @Mapping(target = "estudanteId", source = "estudanteClasse.estudante.id")
    @Mapping(target = "estudanteNome", source = "estudanteClasse.estudante.nome")
    @Mapping(target = "classeId", source = "estudanteClasse.classe.id")
    @Mapping(target = "classeNome", source = "estudanteClasse.classe.turma.nome")
    EstudanteClasseResponse toResponse(EstudanteClasse estudanteClasse);
}

