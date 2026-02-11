package com.ammaPaasam.unavagam.repository;

import com.ammaPaasam.unavagam.entity.base.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RoleRepository extends JpaRepository<Roles, UUID> {
    Roles findByRoleName(String role);
}
