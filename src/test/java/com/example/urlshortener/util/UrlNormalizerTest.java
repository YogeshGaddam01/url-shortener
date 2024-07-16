package com.example.urlshortener.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UrlNormalizerTest {

    @Autowired
    UrlNormalizer urlNormalizer;

//    @Test
//    void normalizeUrlTest1(){
//        String url = "https://www.google.com";
//        String normalizedUrl = "google.com";
//        Assertions.assertEquals(normalizedUrl, urlNormalizer.normalizeUrl(url));
//    }

//    @Test
//    void normalizeUrlTest2(){
//        String url = "http://www.google.com";
//        String normalizedUrl = "google.com";
//        Assertions.assertEquals(normalizedUrl, urlNormalizer.normalizeUrl(url));
//    }

    @Test
    void normalizeUrlTest3(){
        String url = "https://google.com";
        String normalizedUrl = "google.com";
        Assertions.assertEquals(normalizedUrl, urlNormalizer.normalizeUrl(url));
    }

    @Test
    void normalizeUrlTest4(){
        String url = "http://google.com";
        String normalizedUrl = "google.com";
        Assertions.assertEquals(normalizedUrl, urlNormalizer.normalizeUrl(url));
    }

//    @Test
//    void normalizeUrlTest5(){
//        String url = "www.google.com";
//        String normalizedUrl = "google.com";
//        Assertions.assertEquals(normalizedUrl, urlNormalizer.normalizeUrl(url));
//    }

    @Test
    void normalizeUrlTest6(){
        String url = "google.com";
        String normalizedUrl = "google.com";
        Assertions.assertEquals(normalizedUrl, urlNormalizer.normalizeUrl(url));
    }
}
