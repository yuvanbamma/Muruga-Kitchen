package com.ammaPaasam.unavagam.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ErrorResponse {

    private String message;

    private int status;

    private Long timeStamp;
}
