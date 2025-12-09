package com.ClassExchange.usecases.manter_coordenadorCurso;

import com.ClassExchange.domain.entity.CoordenadorCurso;
import com.ClassExchange.domain.entity.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;
import java.util.Optional;

@Repository
public interface CoordenadorCursoRepository extends JpaRepository<CoordenadorCurso, UUID> {
    java.util.List<CoordenadorCurso> findByCurso(Curso curso);
    Optional<CoordenadorCurso> findByCursoIdAndFimIsNull(UUID cursoId);

    @Query("SELECT c FROM CoordenadorCurso c WHERE c.curso.id = :cursoId AND (c.fim IS NULL OR c.fim >= CURRENT_DATE)")
    Optional<CoordenadorCurso> findAtivoByCursoId(@Param("cursoId") UUID cursoId);
}
