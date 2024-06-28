package com.example.urlshortener.service;

import com.google.common.hash.Hashing;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import java.nio.charset.StandardCharsets;

@Service
public class UrlShorteningService{

    @Autowired
    StringRedisTemplate redisTemplate;

    public String getShortenedUrlID(String originalUrl){
        UrlValidator urlValidator = new UrlValidator(new String[] {"http", "https"});
        if(urlValidator.isValid(originalUrl)){
            String id = Hashing.murmur3_32().hashString(originalUrl, StandardCharsets.UTF_8).toString();
            System.out.println("URL id generated: " + id);
            redisTemplate.opsForValue().set(id, originalUrl);
            return id;
        }
        throw new RuntimeException("URL Invalid: " + originalUrl);
    }

    public String getLongUrl(String id){
        String url = redisTemplate.opsForValue().get(id);
        if(url == null){
            throw new RuntimeException("Error in retrieving url, check your id");
        }
        System.out.println("URL Retrieved: " + url);
        return url;
    }

}