package com.example.urlshortener.exception;

import com.example.urlshortener.dto.ResponseObject;
import lombok.Data;

@Data
public class InvalidRequestException extends RuntimeException{

    private ResponseObject errorResponse;

    public InvalidRequestException(ResponseObject errorResponse) {
        this.errorResponse = errorResponse;
    }

}
