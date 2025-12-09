package com.ClassExchange.usecases.manter_diretorEnsino;

import com.ClassExchange.domain.entity.DiretorEnsino;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;
import java.util.Optional;

@Repository
public interface DiretorEnsinoRepository extends JpaRepository<DiretorEnsino, UUID> {
    Optional<DiretorEnsino> findByCampusIdAndFimIsNull(UUID campusId);

    @Query("SELECT d FROM DiretorEnsino d WHERE d.campus.id = :campusId AND (d.fim IS NULL OR d.fim >= CURRENT_DATE)")
    Optional<DiretorEnsino> findAtivoByCampusId(@Param("campusId") UUID campusId);
}
