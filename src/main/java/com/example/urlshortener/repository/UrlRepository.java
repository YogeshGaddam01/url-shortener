package com.example.urlshortener.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UrlRepository {
    @Autowired
    StringRedisTemplate redisTemplate;

    // stores the (id, Url) pair in redis
    public void setID(String id, String originalUrl){
        redisTemplate.opsForValue().set(id, originalUrl);
    }

    // fetches the original long url from redis using the ID
    public String fetchUrl(String id){
        return redisTemplate.opsForValue().get(id);
    }
}
