package com.ClassExchange.usecases.manter_horarios;

import com.ClassExchange.domain.entity.Horario;
import com.ClassExchange.domain.entity.Aula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface HorarioRepository extends JpaRepository<Horario, UUID> {
    List<Horario> findByAula(Aula aula);
}
