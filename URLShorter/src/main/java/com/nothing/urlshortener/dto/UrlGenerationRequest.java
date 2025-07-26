package com.nothing.urlshortener.dto;
import lombok.Data;

@Data
public class UrlGenerationRequest {
    private String longUrl;
}
