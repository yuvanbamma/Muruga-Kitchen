package com.ammaPaasam.unavagam.commonservice;

import com.ammaPaasam.unavagam.exception.ApiException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Component
public class SecurityUItil {

    public static Optional<UUID> getLogedInUserId(){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null || !authentication.isAuthenticated() || Objects.equals(authentication.getPrincipal(), "anonymousUser")){
            return Optional.empty();
        }
        return Optional.of(UUID.fromString(authentication.getName()));
    }
}
