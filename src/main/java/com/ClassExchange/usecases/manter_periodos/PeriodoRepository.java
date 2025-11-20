package com.ClassExchange.usecases.manter_periodos;

import com.ClassExchange.domain.entity.Periodo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
import java.util.Optional;
import java.util.List;

@Repository
public interface PeriodoRepository extends JpaRepository<Periodo, UUID> {
    Optional<Periodo> findBySlug(String slug);
    List<Periodo> findByTurmaId(UUID turmaId);
    List<Periodo> findByCursoId(UUID cursoId);
}
