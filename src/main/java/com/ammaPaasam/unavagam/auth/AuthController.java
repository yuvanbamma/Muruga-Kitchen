package com.ammaPaasam.unavagam.auth;

import com.ammaPaasam.unavagam.dto.LoginRequest;
import com.ammaPaasam.unavagam.dto.LoginResponse;
import com.ammaPaasam.unavagam.dto.UserSignUpRequest;
import com.ammaPaasam.unavagam.dto.UserSignUpResponse;
import com.ammaPaasam.unavagam.entity.User;
import com.ammaPaasam.unavagam.enums.Roles;
import com.ammaPaasam.unavagam.service.AuthService;
import com.ammaPaasam.unavagam.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

       private final UserService userService;

       private final jwtConfig jwtConfig;

       private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) throws Exception {

        User user = userService.findUserByEmail(loginRequest.getEmail());
        String token = jwtConfig.generateToken(user);
        return ResponseEntity.ok(new LoginResponse(token, user.getRole().name()));
    }

    @PostMapping("/registry/food-donor")
    public ResponseEntity<UserSignUpResponse> registerFoodDonor(@RequestBody UserSignUpRequest userSignUpRequest){
        return authService.registerUser(userSignUpRequest, Roles.FOOD_DONATOR);
    }

    @PostMapping("/registry/food-delivery-boy")
    public ResponseEntity<UserSignUpResponse> registerFoodDeliveryBoy(@RequestBody UserSignUpRequest userSignUpRequest){
        return authService.registerUser(userSignUpRequest, Roles.FOOD_DELIVERY_BOY);
    }
}
