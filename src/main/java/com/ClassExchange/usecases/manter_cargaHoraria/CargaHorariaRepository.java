package com.ClassExchange.usecases.manter_cargaHoraria;

import com.ClassExchange.domain.entity.CargaHoraria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CargaHorariaRepository extends JpaRepository<CargaHoraria, UUID> {
}