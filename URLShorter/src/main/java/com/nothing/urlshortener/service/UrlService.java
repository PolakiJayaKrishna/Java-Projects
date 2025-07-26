package com.nothing.urlshortener.service;

import com.nothing.urlshortener.model.UrlMapping;
import com.nothing.urlshortener.repository.UrlRepository;
import org.springframework.stereotype.Service;

@Service
public class UrlService {
    // This is the fully corrected string
    private static final String ALLOWED_CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int BASE = ALLOWED_CHARACTERS.length();

    private final UrlRepository urlRepository;

    public UrlService(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    public String generateShortUrl(String longUrl) {
        // 1. Save the long URL to get a unique ID
        UrlMapping urlMapping = new UrlMapping();
        urlMapping.setLongUrl(longUrl);
        UrlMapping savedUrlMapping = urlRepository.save(urlMapping);

        // 2. Get the unique ID from the saved object
        Long databaseId = savedUrlMapping.getId();

        // 3. Convert the ID to a Base62 string
        StringBuilder shortUrlBuilder = new StringBuilder();

        if (databaseId == 0) {
            return "a";
        }

        while (databaseId > 0) {
            shortUrlBuilder.append(ALLOWED_CHARACTERS.charAt((int) (databaseId % BASE)));
            databaseId /= BASE;
        }

        String shortKey = shortUrlBuilder.reverse().toString();

        // 4. Save the generated short key back to the database record
        savedUrlMapping.setShortKey(shortKey);
        urlRepository.save(savedUrlMapping);

        return shortKey;
    }

    public String getOriginalUrl(String shortKey){
        UrlMapping urlMapping = urlRepository.findByShortKey(shortKey);
        if(urlMapping!= null)
            return urlMapping.getLongUrl();
        return null;
    }

}