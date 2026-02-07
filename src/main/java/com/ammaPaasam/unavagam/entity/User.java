package com.ammaPaasam.unavagam.entity;

import com.ammaPaasam.unavagam.entity.base.Audit;
import com.ammaPaasam.unavagam.enums.Roles;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
public class User extends Audit {

    @Column(length = 5)
    private String countryCode;

    private String country;

    private Double latitude;

    private Double longitude;// for deliver boy to show near by location foods

    private String email;

    private String password;

    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private Roles role; // role reference
}
