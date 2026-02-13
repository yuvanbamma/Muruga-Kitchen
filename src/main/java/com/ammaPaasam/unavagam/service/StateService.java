package com.ammaPaasam.unavagam.service;

import com.ammaPaasam.unavagam.dto.StateListResponse;
import org.springframework.http.ResponseEntity;

public interface StateService {
    ResponseEntity<StateListResponse> getStates();
}
