package com.ammaPaasam.unavagam.service;

import com.ammaPaasam.unavagam.dto.OrphanageRequest;
import com.ammaPaasam.unavagam.dto.OrphanageResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;

public interface OrphanageService {
    ResponseEntity<OrphanageResponse> createOrphanage(@Valid OrphanageRequest orphanageRequest);
}
