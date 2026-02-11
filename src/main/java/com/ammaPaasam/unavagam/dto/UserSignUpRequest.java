package com.ammaPaasam.unavagam.dto;

import com.ammaPaasam.unavagam.enums.Roles;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

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

    private UUID id;

    private String officialName;

    private String registeredNumber; // govt official

    private String contactPersonContact;

    private String bio;

    private String fullAddress;

    private String landmark;

    private String websiteUrl;

    private boolean isVerified; //back office verification.

    private String visitPolicy;

    private String totalChilders;

    private UUID userId;

}
