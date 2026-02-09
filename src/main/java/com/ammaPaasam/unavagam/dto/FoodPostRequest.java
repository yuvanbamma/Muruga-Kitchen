package com.ammaPaasam.unavagam.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
public class FoodPostRequest {

    @NotNull(message = "Name cannot be empty")
    private String name;

    @NotNull(message = "description cannot be empty")
    private String description;

    @NotNull(message = "quantity cannot be empty")
    private Integer quantity;

    private UUID id;

    @NotNull(message = "orphnage id cannot be null")
    private UUID orphaneId;

    private String requirement;

    private Long quantityRequired;

    private Long collectedQuantity;

    private Instant expireTime;

}
