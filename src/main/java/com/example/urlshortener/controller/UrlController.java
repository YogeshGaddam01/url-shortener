package com.example.urlshortener.controller;

import com.example.urlshortener.model.UrlObject;
import com.example.urlshortener.service.UrlShorteningService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/api/v1")
@Slf4j
public class UrlController {

    @Autowired
    UrlShorteningService service;

    @PostMapping
    public String shortenUrl(@RequestBody UrlObject original){

        log.info("Calling the getShortenedUrl method from UrlShorteningService");
        String shortUrl = service.getShortenedUrl(original.getOriginalLink());
        return shortUrl;
    }


}
