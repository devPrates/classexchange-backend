package com.ClassExchange.usecases.manter_classes;

import com.ClassExchange.domain.entity.Classe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ClasseRepository extends JpaRepository<Classe, UUID> {
}