package com.ClassExchange.usecases.manter_disciplinas;

import com.ClassExchange.domain.entity.Curso;
import com.ClassExchange.domain.entity.Disciplina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DisciplinaRepository extends JpaRepository<Disciplina, UUID> {
    List<Disciplina> findByCurso(Curso curso);
}