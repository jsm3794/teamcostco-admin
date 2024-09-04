package com.ezentwix.teamcostco.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.ezentwix.teamcostco.PageMetadataProvider;
import com.ezentwix.teamcostco.dto.product.OrderRequestDTO;
import com.ezentwix.teamcostco.pagination.PaginationRepository;
import com.ezentwix.teamcostco.pagination.PaginationResult;
import com.ezentwix.teamcostco.repository.OrderRequestRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderRequestService implements PageMetadataProvider {
    private final OrderRequestRepository orderRequestRepository;
    private final PaginationRepository paginationRepository;

    public List<OrderRequestDTO> getAll() {
        return orderRequestRepository.getAll();
    }

    public PaginationResult<OrderRequestDTO> getPage(String query, Integer page, Integer size,
            Map<String, Object> params) {
        return paginationRepository.getPage(
                query,
                "OrderRequest.getAll",
                PageRequest.of(page, size),
                params,
                OrderRequestDTO.class);
    }

    @Override
    public String getUri() {
        return "productorder/orderrequest";
    }

    @Override
    public String getPageTitle() {
        return "발주목록";
    }

    @Override
    public List<String> getCssFiles() {
        return List.of("/css/contents/orderrequest.css");
    }

    @Override
    public List<String> getJsFiles() {
        return List.of("/js/contents/orderrequest.js");
    }

}
