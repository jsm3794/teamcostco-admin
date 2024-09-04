package com.ezentwix.teamcostco.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.ezentwix.teamcostco.PageMetadataProvider;
import com.ezentwix.teamcostco.dto.product.NaverProductDTO;
import com.ezentwix.teamcostco.dto.product.NaverProductResponseDTO;
import com.ezentwix.teamcostco.dto.product.OrderRequestDTO;
import com.ezentwix.teamcostco.pagination.PaginationResult;
import com.ezentwix.teamcostco.repository.OrderRequestRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductOrderService implements PageMetadataProvider {
    private WebClient webClient;
    private final OrderRequestRepository orderRequestRepository;

    @Value("${naver.api.client-id}")
    private String clientId;

    @Value("${naver.api.client-secret}")
    private String clientSecret;

    public PaginationResult<NaverProductDTO> getPage(String query, Integer page, Integer size) {
        if (query == null || query.isEmpty() || page == null || page <= 0 || size == null || size <= 0) {
            return new PaginationResult<>(List.of(), 0, 1, 1, 1, 1);
        }

        String url = String.format("https://openapi.naver.com/v1/search/shop.json?query=%s&size=%d&start=%d",
                query, size, (page - 1) * size + 1);

        try {
            if (this.webClient == null) {
                this.webClient = WebClient.builder()
                        .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .defaultHeader("X-Naver-Client-Id", clientId)
                        .defaultHeader("X-Naver-Client-Secret", clientSecret)
                        .build();
            }

            NaverProductResponseDTO response = webClient.get()
                    .uri(url)
                    .retrieve()
                    .bodyToMono(NaverProductResponseDTO.class)
                    .block();

            List<NaverProductDTO> items = response.getItems();
            int count = response.getTotal();
            int totalPages = (int) Math.ceil((double) count / size);
            int showPageNum = 5;
            int startPage = ((page-1) / showPageNum) * showPageNum + 1;
            int endPage = Math.min(startPage + showPageNum - 1, totalPages);

            return new PaginationResult<>(items, count, startPage, endPage, page, totalPages);

        } catch (WebClientResponseException e) {
            throw new RuntimeException("Failed to fetch products from Naver API", e);
        }
    }

    @Transactional
    public void processOrders(List<Map<String, String>> orderData) {
        for (Map<String, String> item : orderData) {
            try {
                OrderRequestDTO orderRequest = new OrderRequestDTO();
                
                // Fetch next request_id
                Integer requestId = orderRequestRepository.getNextRequestId();
                orderRequest.setRequest_id(requestId);
                
                // Convert productId from API to product_code
                Long productCode = Long.parseLong(item.get("productId"));
                orderRequest.setProduct_code(productCode);
                
                orderRequest.setRequest_qty(Integer.parseInt(item.get("quantity")));
                orderRequest.setCreate_date(new Date());
                
                orderRequestRepository.insertOrderRequest(orderRequest);
            } catch (Exception e) {
                e.printStackTrace();
                // Log or handle exception
            }
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

    @Override
    public List<String> getJsFiles() {
        return List.of("/js/contents/productorder.js");
    }
}
