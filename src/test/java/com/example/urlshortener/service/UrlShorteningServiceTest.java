package com.example.urlshortener.service;

import com.example.urlshortener.dto.ResponseObject;
import com.example.urlshortener.model.UrlMapping;
import com.example.urlshortener.dto.UrlObject;
import com.example.urlshortener.repository.UrlRepository;
import com.example.urlshortener.util.HashingUtil;
import com.example.urlshortener.util.UrlNormalizer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
class UrlShorteningServiceTest {

    @Autowired
    private UrlShorteningService urlShorteningService;

    @MockBean
    private HashingUtil hashingUtil;

    @MockBean
    private UrlRepository urlRepository;

    @MockBean
    private UrlNormalizer urlNormalizer;

    private final String originalUrl = "https://www.google.com";
    private final String id = "d2f69089";

    @Test
    void testShortenUrl() {
        UrlMapping urlMapping = new UrlMapping(id, originalUrl);
        String normalizedUrl ="google.com";
        when(urlNormalizer.normalizeUrl(originalUrl)).thenReturn(normalizedUrl);
        when(hashingUtil.getMurmurHashValue(normalizedUrl)).thenReturn(id);
        when(urlRepository.save(urlMapping)).thenReturn(urlMapping);
        Assertions.assertEquals(id, urlShorteningService.getShortenedId(originalUrl));
    }

    @Test
    void testLongUrl(){
        UrlMapping urlMapping = new UrlMapping(id, originalUrl);
        when(urlRepository.findById(id)).thenReturn(Optional.of(urlMapping));
        Assertions.assertEquals(originalUrl, urlShorteningService.getLongUrl(id));
    }

    @Test
    public void testNullUrl() {
        ResponseObject responseObject = urlShorteningService.validateUrl(null);
        Assertions.assertEquals(new ResponseObject("400", "Url Object cannot be null"), responseObject);
    }

    @Test
    public void testNullObjectUrl() {
        ResponseObject responseObject = urlShorteningService.validateUrl(new UrlObject());
        Assertions.assertEquals(new ResponseObject("400", "Url cannot be empty"), responseObject);
    }

    @Test
    public void testEmptyUrl() {
        ResponseObject responseObject = urlShorteningService.validateUrl(new UrlObject(""));
        Assertions.assertEquals(new ResponseObject("400", "Url cannot be empty"), responseObject);
    }

    @Test
    public void testInvalidUrl() {
        ResponseObject responseObject = urlShorteningService.validateUrl(new UrlObject("http//google.com"));
        Assertions.assertEquals(new ResponseObject("400", "Invalid Url"), responseObject);
    }
}
