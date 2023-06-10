package com.wileyedge.libraryapp.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {
    @Value("${api.url}")
    private String apiUrl;

    @Value("${api.search}")
    private String apiSearch;

    @Value("${api.query}")
    private String apiQuery;

    public String getApiUrl() {
        return apiUrl + apiSearch + apiQuery;
    }

    public String getApiUrl(String text) {
        return apiUrl + text + apiQuery;
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}