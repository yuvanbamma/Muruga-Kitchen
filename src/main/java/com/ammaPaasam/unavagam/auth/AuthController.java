package com.ammaPaasam.unavagam.auth;

import com.ammaPaasam.unavagam.commonservice.ValidateUtil;
import com.ammaPaasam.unavagam.dto.LoginRequest;
import com.ammaPaasam.unavagam.dto.LoginResponse;
import com.ammaPaasam.unavagam.dto.UserSignUpRequest;
import com.ammaPaasam.unavagam.dto.UserSignUpResponse;
import com.ammaPaasam.unavagam.entity.User;
import com.ammaPaasam.unavagam.enums.Roles;
import com.ammaPaasam.unavagam.exception.ApiException;
import com.ammaPaasam.unavagam.service.AuthService;
import com.ammaPaasam.unavagam.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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

       private final ValidateUtil validateUtil;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) throws Exception {

        User user = userService.findUserByEmail(loginRequest.getEmail());
        if(!loginRequest.getPassword().equals(user.getPassword())){
            throw new ApiException("Password is wrong", HttpStatus.NOT_FOUND);
        }
        String token = jwtConfig.generateToken(user);
        return ResponseEntity.ok(new LoginResponse(token, validateUtil.getRoleNameByUUID(user.getRole())));
    }

    @PostMapping("/registry/food-donor")
    public ResponseEntity<UserSignUpResponse> registerFoodDonor(@RequestBody UserSignUpRequest userSignUpRequest){
        return authService.registerUser(userSignUpRequest, "FOOD_DONOR");
    }

    @PostMapping("/registry/orphanage")
    public ResponseEntity<UserSignUpResponse> registerFoodDeliveryBoy(@RequestBody UserSignUpRequest userSignUpRequest){
        return authService.registerUser(userSignUpRequest, "ORPHANAGE");
    }
}
