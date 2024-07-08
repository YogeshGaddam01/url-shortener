package com.example.urlshortener.dto;

import lombok.Data;

@Data
public class ResponseObject {
    private String statusCode;
    private String message;

    public ResponseObject(String statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }
}