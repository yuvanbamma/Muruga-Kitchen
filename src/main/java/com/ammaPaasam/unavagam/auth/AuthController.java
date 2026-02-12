package com.ammaPaasam.unavagam.auth;

import com.ammaPaasam.unavagam.commonservice.ValidateUtil;
import com.ammaPaasam.unavagam.dto.LoginRequest;
import com.ammaPaasam.unavagam.dto.LoginResponse;
import com.ammaPaasam.unavagam.dto.UserSignUpRequest;
import com.ammaPaasam.unavagam.dto.UserSignUpResponse;
import com.ammaPaasam.unavagam.entity.Orphanage;
import com.ammaPaasam.unavagam.entity.User;
import com.ammaPaasam.unavagam.enums.Roles;
import com.ammaPaasam.unavagam.exception.ApiException;
import com.ammaPaasam.unavagam.repository.OrphanageRepository;
import com.ammaPaasam.unavagam.service.AuthService;
import com.ammaPaasam.unavagam.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

       private final UserService userService;

       private final jwtConfig jwtConfig;

       private final AuthService authService;

       private final ValidateUtil validateUtil;

       private final OrphanageRepository orphanageRepository;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) throws Exception {

        User user = userService.findUserByEmail(loginRequest.getEmail());
        if(!loginRequest.getPassword().equals(user.getPassword())){
            throw new ApiException("Password is wrong", HttpStatus.NOT_FOUND);
        }
        String token = jwtConfig.generateToken(user);
        Optional<Orphanage> orphanage = orphanageRepository.findByUserIdentity(user.getId());
        UUID orphanageId = null;
        if(orphanage.isPresent()){
            Orphanage orphanage1 = orphanage.get();
            orphanageId = orphanage1.getId();
        }
        return ResponseEntity.ok(new LoginResponse(token, validateUtil.getRoleNameByUUID(user.getRole()),orphanageId,user.getId()));
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
