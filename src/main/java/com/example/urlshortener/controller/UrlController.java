package com.example.urlshortener.controller;

import com.example.urlshortener.service.UrlShorteningService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping(value="/api/v1")
public class UrlController {

    private static final Logger log = LoggerFactory.getLogger(UrlController.class);
    @Autowired
    UrlShorteningService service;

    @GetMapping("{id}")
    public RedirectView redirectView(@PathVariable String id){

        String url = service.getLongUrl(id);
        log.debug("Retrieved Url: {}",url);
        return new RedirectView(url);
    }

    @PostMapping
    public String shortenUrl(@RequestBody String originalUrl){

        String id = service.getShortenedId(originalUrl);
        String fullUrl = ServletUriComponentsBuilder.fromCurrentRequest().toUriString() + '/' + id;
        log.debug("Generated Url: {}",fullUrl);
        return fullUrl;
    }
}

