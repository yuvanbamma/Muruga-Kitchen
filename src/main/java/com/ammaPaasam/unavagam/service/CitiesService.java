package com.ammaPaasam.unavagam.service;

import com.ammaPaasam.unavagam.dto.CityListResponse;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface CitiesService {
    ResponseEntity<CityListResponse> getCityByStateId(UUID stateId);
}
