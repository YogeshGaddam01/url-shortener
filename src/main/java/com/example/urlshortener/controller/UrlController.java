package com.example.urlshortener.controller;

import com.example.urlshortener.dto.ResponseObject;
import com.example.urlshortener.dto.UrlObject;
import com.example.urlshortener.service.UrlShorteningService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(value="/api/v1")
public class UrlController {

    private static final Logger log = LoggerFactory.getLogger(UrlController.class);

    UrlShorteningService urlShorteningService;

    @Autowired
    public UrlController(UrlShorteningService urlShorteningService) {
        this.urlShorteningService = urlShorteningService;
    }

    // Fetches the original long URL stored in redis using the ID and redirects to it.
    @GetMapping("{id}")
    public ResponseEntity<?> redirectView(@PathVariable String id){
        String url = urlShorteningService.getLongUrl(id);
        if(url == null){
            return ResponseEntity.badRequest().body(new ResponseObject("400", "Invalid Id provided"));
        }
        log.debug("Retrieved Url: {}",url);
        return ResponseEntity.ok().body(new ResponseObject("200", url));
    }

    //Generates a Unique ID for the given URL and return the shortened URL
    @PostMapping
    public ResponseEntity<?> shortenUrl(@RequestBody UrlObject urlObject){

        ResponseObject responseObject = urlShorteningService.validateUrl(urlObject);
        if(responseObject != null){
            return ResponseEntity.badRequest().body(responseObject);
        }

        String id = urlShorteningService.getShortenedId(urlObject.getUrl());
        String fullUrl = ServletUriComponentsBuilder.fromCurrentRequest().toUriString();
        if(!fullUrl.endsWith("/")) fullUrl = fullUrl + "/";
        fullUrl = fullUrl + id;
        log.debug("Generated Url: {}",fullUrl);

        return ResponseEntity.ok().body(new ResponseObject("200", fullUrl));
    }
}

