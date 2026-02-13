package com.ammaPaasam.unavagam.entity;

import com.ammaPaasam.unavagam.entity.base.Audit;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class States extends Audit {

    private String stateName;

}
