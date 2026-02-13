package com.ammaPaasam.unavagam.repository;

import com.ammaPaasam.unavagam.entity.Cities;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CityRepository extends JpaRepository<Cities, UUID> {
    List<Cities> findAllByStateId(UUID stateId);
}
