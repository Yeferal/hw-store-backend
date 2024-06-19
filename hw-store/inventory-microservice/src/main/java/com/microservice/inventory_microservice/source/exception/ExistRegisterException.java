package com.microservice.inventory_microservice.source.exception;

public class ExistRegisterException extends RuntimeException{
    public ExistRegisterException(String message) {
        super(message);
    }
}
