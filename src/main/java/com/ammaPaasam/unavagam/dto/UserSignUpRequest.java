package com.ammaPaasam.unavagam.dto;

import com.ammaPaasam.unavagam.enums.Roles;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSignUpRequest {

    private String countryCode;

    private String country;

    private Double latitude;

    private Double longitude;

    private String email;

    private String password;

    private String phoneNumber;
}
