package com.example.urlshortener.exception;

import lombok.Getter;

public class InvalidRequestException extends RuntimeException{

    @Getter
    private final String statusCode;
    private final String message;

    public InvalidRequestException(String statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
