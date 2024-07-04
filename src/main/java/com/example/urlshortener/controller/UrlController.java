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

    // Fetches the original long URL stored in redis using the ID and redirects to it.
    @GetMapping("{id}")
    public RedirectView redirectView(@PathVariable String id){

        String url = service.getLongUrl(id);
        log.debug("Retrieved Url: {}",url);
        return new RedirectView(url);
    }

    //Generates a Unique ID for the given URL and return the shortened URL
    @PostMapping
    public String shortenUrl(@RequestBody String originalUrl){

        String id = service.getShortenedId(originalUrl);
        // Appends the id to the localhost URL
        String fullUrl = ServletUriComponentsBuilder.fromCurrentRequest().toUriString() + '/' + id;
        log.debug("Generated Url: {}",fullUrl);
        return fullUrl;
    }
}

