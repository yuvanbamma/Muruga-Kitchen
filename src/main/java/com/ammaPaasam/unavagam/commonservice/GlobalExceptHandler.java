package com.ammaPaasam.unavagam.commonservice;

import com.ammaPaasam.unavagam.dto.ErrorResponse;
import com.ammaPaasam.unavagam.exception.ApiException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptHandler {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ErrorResponse> handleApiException(ApiException apiException){

        ErrorResponse errorResponse = new ErrorResponse(apiException.getMessage(),apiException.getStatusCode().value(),System.currentTimeMillis());
        return ResponseEntity.status(apiException.getStatusCode()).body(errorResponse);

    }
}
