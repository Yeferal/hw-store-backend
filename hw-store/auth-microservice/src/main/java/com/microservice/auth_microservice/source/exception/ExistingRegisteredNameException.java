package com.microservice.auth_microservice.source.exception;

public class ExistingRegisteredNameException extends AuthServiceSystemException {

    public ExistingRegisteredNameException(String message) {
        super(message);
    }
}
