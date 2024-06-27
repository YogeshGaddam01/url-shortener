package com.example.urlshortener.model;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class UrlObject {
    private String originalLink;

    public UrlObject(String originalLink) {
        this.originalLink = originalLink;
    }

    public String getOriginalLink() {
        return originalLink;
    }

    public void setOriginalLink(String originalLink) {
        this.originalLink = originalLink;
    }
}
