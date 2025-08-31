package com.ClassExchange.usecases.manter_cursos;

import com.ClassExchange.entity.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CursoRepository extends JpaRepository<Curso, UUID> {
}