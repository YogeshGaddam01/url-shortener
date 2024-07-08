package com.example.urlshortener.repository;

import com.example.urlshortener.model.UrlMapping;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.context.annotation.ComponentScan;
import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
@AutoConfigureDataMongo
@ComponentScan(basePackages = "com.example.urlshortener.repository")
public class UrlRepositoryTest {

    @Autowired
    private UrlRepository urlRepository;

    @Test
    public void testSaveAndFetchUrl() {
        String id = "d2f69089";
        String originalUrl = "https://www.google.com";

        UrlMapping urlMapping = new UrlMapping(id, originalUrl);
        urlRepository.save(urlMapping);

        UrlMapping fetchedUrlMapping = urlRepository.findById(id).orElse(null);

        assertNotNull(fetchedUrlMapping);
        assertEquals(originalUrl, fetchedUrlMapping.getOriginalUrl());
    }
}
