package com.ammaPaasam.unavagam.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class OrphanageRequest {

    private UUID id;

    private String officialName;

    private String registeredNumber; // govt official

    private String contactPersonContact;

    private String email;

    private String bio;

    private String fullAddress;

    private Double latitude;

    private Double longitude;

    private String landmark;

    private String websiteUrl;

    private boolean isVerified; //back office verification.

    private String visitPolicy;

    private String totalChilders;

}
