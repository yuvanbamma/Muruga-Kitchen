package com.ammaPaasam.unavagam.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;


@RequiredArgsConstructor
public class ApiException extends RuntimeException{

    private final HttpStatusCode statusCode;

    public ApiException(String message,  HttpStatusCode statusCode){
        super(message);
        this.statusCode = statusCode;
    }

    public HttpStatusCode getStatusCode(){
        return statusCode;
    }
}
