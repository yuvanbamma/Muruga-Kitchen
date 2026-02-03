package com.ammaPaasam.unavagam.repository;

import com.ammaPaasam.unavagam.entity.FoodPost;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface FoodPostRepository extends JpaRepository<FoodPost, UUID> {
    Page<FoodPost> findByIsDeletedFalse(Pageable pageable);

    Optional<FoodPost> findByIdAndIsDeletedFalse(UUID id);

}
