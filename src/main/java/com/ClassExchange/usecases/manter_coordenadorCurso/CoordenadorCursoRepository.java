package com.ClassExchange.usecases.manter_coordenadorCurso;

import com.ClassExchange.domain.entity.CoordenadorCurso;
import com.ClassExchange.domain.entity.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
import java.util.Optional;

@Repository
public interface CoordenadorCursoRepository extends JpaRepository<CoordenadorCurso, UUID> {
    java.util.List<CoordenadorCurso> findByCurso(Curso curso);
    Optional<CoordenadorCurso> findByCursoIdAndFimIsNull(UUID cursoId);
}
