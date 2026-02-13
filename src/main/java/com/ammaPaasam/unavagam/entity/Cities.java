package com.ammaPaasam.unavagam.entity;

import com.ammaPaasam.unavagam.entity.base.Audit;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
public class Cities extends Audit {

    private UUID stateId;

    private String cityName;

    private String pinCode;

}
