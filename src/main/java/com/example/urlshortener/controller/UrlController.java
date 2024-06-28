package com.example.urlshortener.controller;

import com.example.urlshortener.service.UrlShorteningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(value="/api/v1")
public class UrlController {

    @Autowired
    UrlShorteningService service;

    @GetMapping("{id}")
    public RedirectView redirectView(@PathVariable String id){

        String url = service.getLongUrl(id);
        return new RedirectView(url);
    }

    @PostMapping
    public String shortenUrl(@RequestBody String originalUrl){

        String id = service.getShortenedUrlID(originalUrl);
        String fullUrl = ServletUriComponentsBuilder.fromCurrentRequest().toUriString();
        return fullUrl+'/'+id;
    }
}

