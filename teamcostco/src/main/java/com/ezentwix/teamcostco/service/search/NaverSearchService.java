package com.ezentwix.teamcostco.service.search;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@Service
public class NaverSearchService {

    @Value("${naver.api.client-id}")
    private String clientId;

    @Value("${naver.api.client-secret}")
    private String clientSecret;

    private final WebClient webClient;

    public NaverSearchService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://openapi.naver.com/v1/search").build();
    }

    public Mono<String> search(String query, Integer display, Integer start) {
        return this.webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/shop.json")
                        .queryParam("query", query)
                        .queryParam("display",display)
                        .queryParam("start", start)
                        .build())
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header("X-Naver-Client-Id", clientId)
                .header("X-Naver-Client-Secret", clientSecret)
                .retrieve()
                .bodyToMono(String.class);
    }
    
}
