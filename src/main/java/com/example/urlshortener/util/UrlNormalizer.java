package com.example.urlshortener.util;

import org.springframework.stereotype.Component;

@Component
public class UrlNormalizer {
    public String normalizeUrl(String url) {

        if(url.startsWith("https://")){
            url = url.substring(8);
        }
        else if(url.startsWith("http://")){
            url = url.substring(7);
        }

        if(url.startsWith("www.")){
            url = url.substring(4);
        }
        return url;
    }
}
