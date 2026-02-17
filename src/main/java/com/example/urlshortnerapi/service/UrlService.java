package com.example.urlshortnerapi.service;

import com.example.urlshortnerapi.entity.UrlMapping;
import com.example.urlshortnerapi.repository.UrlRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UrlService {

    private final UrlRepository repository;
    private final ShortCodeGenerator generator;

    public String shortenUrl(String longUrl) {

        String shortCode;

        do {
            shortCode = generator.generate();
        } while (repository.existsByShortCode(shortCode));

        UrlMapping mapping = new UrlMapping();
        mapping.setLongUrl(longUrl);
        mapping.setShortCode(shortCode);

        repository.save(mapping);

        return shortCode;
    }

    public String getLongUrl(String shortCode) {
        return repository.findByShortCode(shortCode)
                .map(UrlMapping::getLongUrl)
                .orElseThrow(() -> new RuntimeException("Short URL not found"));
    }
}

