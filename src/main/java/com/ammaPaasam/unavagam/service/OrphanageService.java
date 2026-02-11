package com.ammaPaasam.unavagam.service;

import com.ammaPaasam.unavagam.dto.OrphanageRequest;
import com.ammaPaasam.unavagam.dto.OrphanageResponse;
import com.ammaPaasam.unavagam.dto.UserSignUpRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;

public interface OrphanageService {
    ResponseEntity<OrphanageResponse> createOrphanage(UserSignUpRequest userSignUpRequest);
}
