package com.ClassExchange.usecases.manter_rolePermission;

import com.ClassExchange.domain.entity.Permission;
import com.ClassExchange.domain.entity.Role;
import com.ClassExchange.domain.entity.RolePermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RolePermissionRepository extends JpaRepository<RolePermission, UUID> {
    Optional<RolePermission> findByRoleAndPermission(Role role, Permission permission);
}

