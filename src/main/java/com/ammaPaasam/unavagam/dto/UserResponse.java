package com.ammaPaasam.unavagam.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UserResponse {

    private String countryCode;

    private String country;

    private Double latitude;

    private Double longitude;// for deliver boy to show near by location foods

    private String email;

    private String phoneNumber;
}
