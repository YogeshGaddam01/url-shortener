package com.example.urlshortener.util;

import com.google.common.hash.Hashing;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Component
public class HashingUtil {

    public String getMurmurHashValue(String url){
        return Hashing.murmur3_32().hashString(url, StandardCharsets.UTF_8).toString();
    }
}
