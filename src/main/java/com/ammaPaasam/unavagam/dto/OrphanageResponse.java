package com.ammaPaasam.unavagam.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class OrphanageResponse {

    private UUID id;

    private UUID userIdentity;

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

    private boolean isVerified; // back office verification.

    private String visitPolicy;

    private String totalChilders;

    private String message;

}
