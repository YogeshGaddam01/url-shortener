package com.example.urlshortener.model;

import lombok.Data;

@Data
public class Response {
    private String statusCode;
    private String message;

    public Response(String statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }
}