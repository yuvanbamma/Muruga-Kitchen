package com.ammaPaasam.unavagam.service.impl;

import com.ammaPaasam.unavagam.commonservice.ValidateUtil;
import com.ammaPaasam.unavagam.dto.LoginResponse;
import com.ammaPaasam.unavagam.dto.UserSignUpRequest;
import com.ammaPaasam.unavagam.dto.UserSignUpResponse;
import com.ammaPaasam.unavagam.entity.User;
import com.ammaPaasam.unavagam.enums.Roles;
import com.ammaPaasam.unavagam.exception.ApiException;
import com.ammaPaasam.unavagam.repository.UserRepository;
import com.ammaPaasam.unavagam.service.AuthService;
import com.ammaPaasam.unavagam.service.OrphanageService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.beans.Transient;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    private final ValidateUtil validateUtil;

    private final OrphanageService orphanageService;

    @Transactional
    @Override
    public ResponseEntity<UserSignUpResponse> registerUser(UserSignUpRequest userSignUpRequest, String role) {

        if (userRepository.existsByEmail(userSignUpRequest.getEmail())) {
            throw new ApiException("User already exists for this email.", HttpStatus.BAD_GATEWAY);
        }
        UUID roleId = validateUtil.validateAndGetRoleUUId(role);
        User user = new User();
        user.setEmail(userSignUpRequest.getEmail());
        user.setPassword(userSignUpRequest.getPassword());
        user.setLongitude(userSignUpRequest.getLongitude());
        user.setLatitude(userSignUpRequest.getLatitude());
        user.setRole(roleId);
        user.setCountry(userSignUpRequest.getCountry());
        user.setCountryCode(userSignUpRequest.getCountryCode());
        user.setPhoneNumber(userSignUpRequest.getPhoneNumber());
        user.setCityId(userSignUpRequest.getCityId());
        user.setStateId(userSignUpRequest.getStateId());
        User userPersistent = userRepository.save(user);

        if (role.equals("ORPHANAGE")) {
            userSignUpRequest.setUserId(userPersistent.getId());
            orphanageService.createOrphanage(userSignUpRequest);
        }

        UserSignUpResponse userSignUpResponse = new UserSignUpResponse();
        userSignUpResponse.setMessage("User succesfully signed up");

        return ResponseEntity.ok(userSignUpResponse);
    }
}
