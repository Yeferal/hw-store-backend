package com.microservice.sales_microservice.source.exception;

public class RegisterNotFoundException extends RuntimeException{
    public RegisterNotFoundException(String message) {
        super(message);
    }
}
