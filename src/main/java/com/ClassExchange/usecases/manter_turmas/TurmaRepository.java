package com.ClassExchange.usecases.manter_turmas;

import com.ClassExchange.domain.entity.Turma;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TurmaRepository extends JpaRepository<Turma, UUID> {
}