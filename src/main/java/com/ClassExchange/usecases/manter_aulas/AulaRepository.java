package com.ClassExchange.usecases.manter_aulas;

import com.ClassExchange.domain.entity.Aula;
import com.ClassExchange.domain.entity.Periodo;
import com.ClassExchange.domain.entity.Disciplina;
import com.ClassExchange.domain.entity.Usuario;
import com.ClassExchange.domain.entity.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AulaRepository extends JpaRepository<Aula, UUID> {
    List<Aula> findByPeriodo(Periodo periodo);
    List<Aula> findByDisciplina(Disciplina disciplina);
    List<Aula> findByProfessor(Usuario professor);
    List<Aula> findByLocal(Local local);
}

