package com.ezentwix.teamcostco.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ezentwix.teamcostco.PageMetadataProvider;
import com.ezentwix.teamcostco.dto.product.OrderRequestDTO;
import com.ezentwix.teamcostco.repository.OrderRequestRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderRequestDetailService implements PageMetadataProvider {
    private final OrderRequestRepository orderRequestRepository;

    // 발주 정보 조회
    public OrderRequestDTO getById(Integer requestId) {
        return orderRequestRepository.getById(requestId);
    }

    public boolean exist(Long product_code) {
        return orderRequestRepository.exist(product_code);
    }

    public void newProduct(OrderRequestDTO dto) {
        orderRequestRepository.newProduct(dto);
    }

    public void updateQTY(OrderRequestDTO dto) {
        orderRequestRepository.updateQTY(dto);
    }

    public void complete(OrderRequestDTO dto) {
        orderRequestRepository.complete(dto);
    }

    public void defectiveProduct(OrderRequestDTO dto) {
        orderRequestRepository.defectiveProduct(dto);
    }   



    @Override
    public String getUri() {
        return "/orderrequest/orderrequest_detail";
    }

    @Override
    public String getPageTitle() {
        return "발주관리";
    }

    @Override
    public List<String> getCssFiles() {
        return List.of("/css/contents/orderrequest_detail.css");
    }

    @Override
    public List<String> getJsFiles() {
        return List.of("/js/contents/ordereaquest_detail.js");
    }
}
