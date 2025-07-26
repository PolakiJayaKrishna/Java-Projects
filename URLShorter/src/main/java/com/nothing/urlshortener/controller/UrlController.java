package com.nothing.urlshortener.controller;

import com.nothing.urlshortener.service.UrlService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.nothing.urlshortener.dto.UrlGenerationRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.io.IOException;

@RestController
@RequestMapping("/api/v1")
public class UrlController {
    private final UrlService urlService;

    public UrlController(UrlService urlService){
        this.urlService = urlService;
    }
    @PostMapping("/generate")
    public String genarateShortUrl(@RequestBody UrlGenerationRequest request){
        return urlService.generateShortUrl(request.getLongUrl());
    }

    @GetMapping("/{shortKey}")
    public void redirectToOriginalUrl(@PathVariable String shortKey, HttpServletResponse response) throws IOException {
        String originalUrl = urlService.getOriginalUrl(shortKey);

        if (originalUrl != null) {
            response.sendRedirect(originalUrl);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}