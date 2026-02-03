package com.ammaPaasam.unavagam.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.message.Message;

@Getter
@Setter
public class FoodPostRequest {

    @NotNull(message = "Name cannot be empty")
    private String name;

    @NotNull(message = "description cannot be empty")
    private String description;

    @NotNull(message = "quantity cannot be empty")
    private Integer quantity;
}
