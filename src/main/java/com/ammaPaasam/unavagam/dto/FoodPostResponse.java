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

    private Integer quantity;

    private String imageUrl;

    private Instant createdAt;

    private Instant updatedAt;

    private boolean isActive;

    private boolean isDeleted;

}
