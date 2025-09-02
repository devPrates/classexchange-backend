package com.ClassExchange.usecases.manter_diretorEnsino;

import com.ClassExchange.domain.entity.DiretorEnsino;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DiretorEnsinoRepository extends JpaRepository<DiretorEnsino, UUID> {
}