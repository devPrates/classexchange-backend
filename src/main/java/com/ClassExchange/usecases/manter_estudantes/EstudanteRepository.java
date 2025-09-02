package com.ClassExchange.usecases.manter_estudantes;

import com.ClassExchange.domain.entity.Estudante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EstudanteRepository extends JpaRepository<Estudante, UUID> {
}