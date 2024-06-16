package com.microservice.auth_microservice.source.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthServiceSystemException extends Exception{

    private Error error;
    public AuthServiceSystemException(String message){
        this.error = new Error(message);
    }

}
