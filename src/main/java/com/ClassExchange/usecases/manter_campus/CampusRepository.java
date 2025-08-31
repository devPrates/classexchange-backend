package com.ClassExchange.usecases.manter_campus;


import com.ClassExchange.entity.Campus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CampusRepository extends JpaRepository<Campus, UUID> {
}
