package com.ClassExchange.usecases.manter_professorDisciplina;

import com.ClassExchange.domain.entity.ProfessorDisciplina;
import com.ClassExchange.domain.entity.Usuario;
import com.ClassExchange.domain.entity.DisciplinaTurma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProfessorDisciplinaRepository extends JpaRepository<ProfessorDisciplina, UUID> {
    List<ProfessorDisciplina> findByUsuario(Usuario usuario);
    List<ProfessorDisciplina> findByDisciplinaTurma(DisciplinaTurma disciplinaTurma);
}