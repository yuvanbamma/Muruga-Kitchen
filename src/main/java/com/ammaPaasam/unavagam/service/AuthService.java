package com.ammaPaasam.unavagam.service;

import com.ammaPaasam.unavagam.dto.LoginResponse;
import com.ammaPaasam.unavagam.dto.UserSignUpRequest;
import com.ammaPaasam.unavagam.dto.UserSignUpResponse;
import com.ammaPaasam.unavagam.enums.Roles;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<UserSignUpResponse> registerUser(UserSignUpRequest userSignUpRequest, Roles roles);
}
