package com.ClassExchange.usecases.manter_professorCurso;

import com.ClassExchange.domain.entity.ProfessorCurso;
import com.ClassExchange.domain.entity.Usuario;
import com.ClassExchange.domain.entity.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProfessorCursoRepository extends JpaRepository<ProfessorCurso, UUID> {
    List<ProfessorCurso> findByUsuario(Usuario usuario);
    List<ProfessorCurso> findByCurso(Curso curso);
    long countByCurso(Curso curso);
}
