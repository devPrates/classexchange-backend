package com.ClassExchange.usecases.manter_estudantesClasses;

import com.ClassExchange.domain.entity.EstudanteClasse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EstudanteClasseRepository extends JpaRepository<EstudanteClasse, UUID> {
}