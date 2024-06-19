package com.microservice.inventory_microservice.source.exception;

public class FailedRegisterException extends RuntimeException{
    public FailedRegisterException(String message) {
        super(message);
    }
}
