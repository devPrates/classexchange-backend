package com.ClassExchange.usecases.manter_professorClasse;

import com.ClassExchange.domain.entity.ProfessorClasse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProfessorClasseRepository extends JpaRepository<ProfessorClasse, UUID> {
}