package com.ammaPaasam.unavagam.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
public class FoodPostResponse {

    private String name;

    private UUID id;

    private String description;

    private String imageUrl;

    private Instant createdAt;

    private Instant updatedAt;

    private boolean isActive;

    private boolean isDeleted;

    private UUID orphaneId;

    private String requirement;

    private Long quantityRequired;

    private Long collectedQuantity;

    private Instant expireTime;

    private Double distance; //distance in km

}
