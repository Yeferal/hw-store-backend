package com.microservice.sales_microservice.source.exception;

public class ExistRegisterException extends RuntimeException{
    public ExistRegisterException(String message) {
        super(message);
    }
}
