package com.ClassExchange.usecases.manter_disciplinaTurma;

import com.ClassExchange.domain.entity.DisciplinaTurma;
import com.ClassExchange.domain.entity.Disciplina;
import com.ClassExchange.domain.entity.Turma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DisciplinaTurmaRepository extends JpaRepository<DisciplinaTurma, UUID> {
    List<DisciplinaTurma> findByDisciplina(Disciplina disciplina);
    List<DisciplinaTurma> findByTurma(Turma turma);
}