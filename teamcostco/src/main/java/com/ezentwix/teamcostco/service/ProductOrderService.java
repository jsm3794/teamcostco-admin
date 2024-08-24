package com.ezentwix.teamcostco.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.ezentwix.teamcostco.PageMetadataProvider;
import com.ezentwix.teamcostco.dto.product.NaverProductResponseDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductOrderService implements PageMetadataProvider {

    @Value("${naver.api.client-id}")
    private String clientId;

    @Value("${naver.api.client-secret}")
    private String clientSecret;

    private final WebClient.Builder webClientBuilder;

    public NaverProductResponseDTO search(String query, Integer display, Integer start) {
        String url = String.format("https://openapi.naver.com/v1/search/shop.json?query=%s&display=%d&start=%d",
                query, display, start);

        WebClient webClient = webClientBuilder
                .baseUrl(url)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader("X-Naver-Client-Id", clientId)
                .defaultHeader("X-Naver-Client-Secret", clientSecret)
                .build();

        try {
            return webClient.get()
                    .retrieve()
                    .bodyToMono(NaverProductResponseDTO.class)
                    .block(); // block()을 호출하여 동기적으로 응답을 받습니다.
        } catch (WebClientResponseException e) {
            // Handle the exception based on your application's needs
            throw new RuntimeException("Failed to fetch products from Naver API", e);
        }
    }

    @Override
    public String getUri() {
        return "productorder/productorder";
    }

    @Override
    public String getPageTitle() {
        return "상품발주";
    }

    @Override
    public List<String> getCssFiles() {
        return List.of("/css/contents/productorder.css");
    }
}
