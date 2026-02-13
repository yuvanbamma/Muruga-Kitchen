package com.ammaPaasam.unavagam.repository;

import com.ammaPaasam.unavagam.entity.States;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StateRepository extends JpaRepository<States, UUID> {
}
