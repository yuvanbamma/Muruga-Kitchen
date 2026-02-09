package com.ammaPaasam.unavagam.repository;

import com.ammaPaasam.unavagam.entity.Orphanage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrphanageRepository extends JpaRepository<Orphanage,UUID> {
}
