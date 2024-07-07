package com.example.urlshortener.controller;

import com.example.urlshortener.model.Response;
import com.example.urlshortener.model.UrlObject;
import com.example.urlshortener.service.UrlShorteningService;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping(value="/api/v1")
public class UrlController {

    private static final Logger log = LoggerFactory.getLogger(UrlController.class);

    UrlShorteningService service;

    @Autowired
    public UrlController(UrlShorteningService service) {
        this.service = service;
    }

    // Fetches the original long URL stored in redis using the ID and redirects to it.
    @GetMapping("{id}")
    public ResponseEntity<?> redirectView(@PathVariable String id){
        String url = service.getLongUrl(id);
        if(url == null){
            return ResponseEntity.badRequest().body(new Response("400", "Invalid Id provided"));
        }
        log.debug("Retrieved Url: {}",url);
        return ResponseEntity.ok().body(new Response("200", url));
    }

    //Generates a Unique ID for the given URL and return the shortened URL
    @PostMapping
    public ResponseEntity<?> shortenUrl(@RequestBody UrlObject urlObject){

        ResponseEntity<?> responseEntity = service.validateUrl(urlObject);
        if(responseEntity != null){
            return responseEntity;
        }
        String id = service.getShortenedId(urlObject.getUrl());
        String fullUrl = ServletUriComponentsBuilder.fromCurrentRequest().toUriString() + '/' + id;
        log.debug("Generated Url: {}",fullUrl);

        return ResponseEntity.ok().body(new Response("200", fullUrl));
    }
}

