package com.ammaPaasam.unavagam.entity;

import com.ammaPaasam.unavagam.entity.base.Audit;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Entity
@Getter
@Setter
public class FoodPost extends Audit {

    @Column(nullable = false)
    private String name;

    private UUID orphaneId;

    @Column(length = 500)
    private String description;

    @Column
    private String requirement;

    @Column
    private Long quantityRequired;

    @Column
    private Long collectedQuantity;

    private Instant expireTime;

    @Column
    private String imageUrl;

}
