package com.anonymous.mentalcare.jwt;

public class ApiRequestException extends IllegalArgumentException {

    public ApiRequestException(String message) {
        super(message);
    }
}