package com.example.urlshortener.dto;

import lombok.Data;

@Data
public class ResponseObject {
    private String statusCode;
    private String message;
    private String url;

    public ResponseObject(String statusCode, String message, String url) {
        this.statusCode = statusCode;
        this.message = message;
        this.url = url;
    }
    public ResponseObject(String statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
        this.url = null;
    }
}