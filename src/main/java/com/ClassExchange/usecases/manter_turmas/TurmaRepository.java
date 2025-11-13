package com.ClassExchange.usecases.manter_turmas;

import com.ClassExchange.domain.entity.Curso;
import com.ClassExchange.domain.entity.Turma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TurmaRepository extends JpaRepository<Turma, UUID> {
    List<Turma> findByCurso(Curso curso);
    Optional<Turma> findBySlug(String slug);
}
