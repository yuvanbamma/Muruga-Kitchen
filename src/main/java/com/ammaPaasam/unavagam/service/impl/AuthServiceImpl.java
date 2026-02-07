package com.ammaPaasam.unavagam.service.impl;

import com.ammaPaasam.unavagam.dto.LoginResponse;
import com.ammaPaasam.unavagam.dto.UserSignUpRequest;
import com.ammaPaasam.unavagam.dto.UserSignUpResponse;
import com.ammaPaasam.unavagam.entity.User;
import com.ammaPaasam.unavagam.enums.Roles;
import com.ammaPaasam.unavagam.exception.ApiException;
import com.ammaPaasam.unavagam.repository.UserRepository;
import com.ammaPaasam.unavagam.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {


    private final UserRepository userRepository;

    @Override
    public ResponseEntity<UserSignUpResponse> registerUser(UserSignUpRequest userSignUpRequest, Roles roles) {

        if (userRepository.existsByEmail(userSignUpRequest.getEmail())) {
            throw new ApiException("User already exists for this email.", HttpStatus.BAD_GATEWAY);
        }

        User user = new User();
        user.setEmail(userSignUpRequest.getEmail());
        user.setPassword(userSignUpRequest.getPassword());
        user.setLongitude(userSignUpRequest.getLongitude());
        user.setLatitude(userSignUpRequest.getLatitude());
        user.setRole(roles);
        user.setCountry(userSignUpRequest.getCountry());
        user.setCountryCode(userSignUpRequest.getCountryCode());
        user.setPhoneNumber(userSignUpRequest.getPhoneNumber());
        userRepository.save(user);

        UserSignUpResponse userSignUpResponse = new UserSignUpResponse();
        userSignUpResponse.setMessage("User succesfully signed up");


        return ResponseEntity.ok(userSignUpResponse);
    }
}
