package com.ezentwix.teamcostco.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ezentwix.teamcostco.PageMetadataProvider;
import com.ezentwix.teamcostco.dto.product.OrderRequestDTO;
import com.ezentwix.teamcostco.repository.OrderRequestRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderRequestProcessService implements PageMetadataProvider {
    private final OrderRequestRepository orderRequestRepository;

    // 발주 정보 조회
    public OrderRequestDTO getById(Integer requestId) {
        return orderRequestRepository.getById(requestId);
    }

   

    @Override
    public String getUri() {
        return "/orderrequest/orderrequest_process";
    }

    @Override
    public String getPageTitle() {
        return "발주관리";
    }

    @Override
    public List<String> getCssFiles() {
        return List.of("/css/contents/orderrequest_process.css");
    }

    @Override
    public List<String> getJsFiles() {
        return List.of("/js/contents/InventoryManagement.js");
    }
}
