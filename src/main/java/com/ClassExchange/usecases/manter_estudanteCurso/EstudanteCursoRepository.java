package com.ClassExchange.usecases.manter_estudanteCurso;

import com.ClassExchange.domain.entity.EstudanteCurso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface EstudanteCursoRepository extends JpaRepository<EstudanteCurso, UUID> {
    List<EstudanteCurso> findByCursoId(UUID cursoId);
    long countByCursoId(UUID cursoId);
}