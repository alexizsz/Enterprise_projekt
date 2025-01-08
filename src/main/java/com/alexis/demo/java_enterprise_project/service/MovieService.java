package com.alexis.demo.java_enterprise_project.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class MovieService {

    private final RestTemplate restTemplate;
    @Value("${tmdb.api.key}")
    private String apiKey;

    public MovieService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String searchMovies(String query) {
        String url = UriComponentsBuilder.fromHttpUrl("https://api.themoviedb.org/3/search/movie")
                .queryParam("api_key", apiKey)
                .queryParam("query", query)
                .toUriString();
        return restTemplate.getForObject(url, String.class);
    }
}
