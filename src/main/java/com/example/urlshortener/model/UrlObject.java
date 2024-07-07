package com.example.urlshortener.model;

import lombok.Data;

@Data
public class UrlObject {
    private String url;

    public UrlObject(){}

    public UrlObject(String url){
        this.url = url;
    }
}
