package com.ClassExchange.usecases.manter_estudanteDisciplina;

import com.ClassExchange.domain.entity.EstudanteDisciplina;
import com.ClassExchange.domain.entity.Estudante;
import com.ClassExchange.domain.entity.DisciplinaTurma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface EstudanteDisciplinaRepository extends JpaRepository<EstudanteDisciplina, UUID> {
    List<EstudanteDisciplina> findByEstudante(Estudante estudante);
    List<EstudanteDisciplina> findByDisciplinaTurma(DisciplinaTurma disciplinaTurma);
}