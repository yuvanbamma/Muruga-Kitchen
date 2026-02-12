package com.ammaPaasam.unavagam.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class LoginResponse {

    private String token;

    private String roleName;

    private UUID orphanageId;

    private UUID userId;
}
