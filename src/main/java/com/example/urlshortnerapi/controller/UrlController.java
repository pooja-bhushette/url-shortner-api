package com.example.urlshortnerapi.controller;

import com.example.urlshortnerapi.entity.UrlMapping;
import com.example.urlshortnerapi.entity.UrlRequest;
import com.example.urlshortnerapi.repository.UrlRepository;
import com.example.urlshortnerapi.service.UrlService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;


@RestController
@RequiredArgsConstructor
public class UrlController {
    private final UrlService urlService;
    private final UrlRepository urlRepository;

    @PostMapping("/shorten")
    public String createShortUrl(@RequestBody UrlRequest urlRequest) {
        return urlService.shortenUrl(urlRequest.getLongUrl());
    }

    @GetMapping("/{shortCode}")
    public ResponseEntity<Void> redirect(@PathVariable String shortCode) {

        String longUrl = urlService.getLongUrl(shortCode);
       return ResponseEntity
                .status(HttpStatus.FOUND)
                .location(URI.create(longUrl))
                .build();


    }
}
