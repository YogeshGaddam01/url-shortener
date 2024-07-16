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

        /*
            Few links wont work:
            Returns not found: https://www.blog.codinghorror.com/url-shortening-hashes-in-practice/
            Returns the page: https://blog.codinghorror.com/url-shortening-hashes-in-practice/

            Problem: https://superuser.com/questions/453673/some-websites-dont-work-with-the-www-prefix

            Links with and without the www subdomain may map to different pages due to
            misconfigured DNS or a misconfigured configuration
         */
//        if(url.startsWith("www.")){
//            url = url.substring(4);
//        }

        return url;
    }
}
