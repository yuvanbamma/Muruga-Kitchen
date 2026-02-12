package com.ammaPaasam.unavagam.service.impl;

import com.ammaPaasam.unavagam.UserNotFoundException;
import com.ammaPaasam.unavagam.dto.UserResponse;
import com.ammaPaasam.unavagam.entity.User;
import com.ammaPaasam.unavagam.exception.ApiException;
import com.ammaPaasam.unavagam.repository.UserRepository;
import com.ammaPaasam.unavagam.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ApiException("User not found with email please signup to create account: " + email, HttpStatus.BAD_GATEWAY));
    }

    @Override
    public UserResponse findById(UUID userId) {
        User user =  userRepository.findById(userId).orElseThrow(() -> new ApiException("No user found for this id.",HttpStatus.NOT_FOUND));
        UserResponse response = new UserResponse();
        BeanUtils.copyProperties(user,response);
        return response;
    }


}
