package com.ClassExchange.usecases.manter_estudanteDisciplina;

import com.ClassExchange.domain.entity.EstudanteDisciplina;
import com.ClassExchange.domain.entity.EstudanteCurso;
import com.ClassExchange.domain.entity.Aula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface EstudanteDisciplinaRepository extends JpaRepository<EstudanteDisciplina, UUID> {
    List<EstudanteDisciplina> findByEstudanteCurso(EstudanteCurso estudanteCurso);
    List<EstudanteDisciplina> findByAula(Aula aula);
    Optional<EstudanteDisciplina> findByEstudanteCursoAndAula(EstudanteCurso estudanteCurso, Aula aula);
}
