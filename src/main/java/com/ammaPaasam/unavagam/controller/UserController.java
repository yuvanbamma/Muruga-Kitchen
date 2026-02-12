package com.ammaPaasam.unavagam.controller;

import com.ammaPaasam.unavagam.dto.UserResponse;
import com.ammaPaasam.unavagam.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<UserResponse> getUserById(UUID userId){
        UserResponse userResponse = userService.findById(userId);
        return ResponseEntity.ok(userResponse);
    }
}
