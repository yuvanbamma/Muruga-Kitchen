package com.ammaPaasam.unavagam.entity.base;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Roles extends Audit{

    private String roleName;

    private String description;
}
