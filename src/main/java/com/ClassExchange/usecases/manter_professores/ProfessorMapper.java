package com.ClassExchange.usecases.manter_professores;

import com.ClassExchange.domain.entity.Professor;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProfessorMapper {
    ProfessorResponse toResponse(Professor professor);
}

