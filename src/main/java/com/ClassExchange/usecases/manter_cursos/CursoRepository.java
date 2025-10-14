package com.ClassExchange.usecases.manter_cursos;

import com.ClassExchange.domain.entity.Campus;
import com.ClassExchange.domain.entity.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CursoRepository extends JpaRepository<Curso, UUID> {
    List<Curso> findByCampus(Campus campus);
    Optional<Curso> findBySlug(String slug);
}