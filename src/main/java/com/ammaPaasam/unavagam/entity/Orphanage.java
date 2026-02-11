package com.ammaPaasam.unavagam.entity;

import com.ammaPaasam.unavagam.entity.base.Audit;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
public class Orphanage extends Audit {


    private UUID userId;

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
