package com.ezentwix.teamcostco.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ezentwix.teamcostco.PageMetadataProvider;
import com.ezentwix.teamcostco.dto.product.OrderRequestDTO;
import com.ezentwix.teamcostco.repository.OrderRequestRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderRequestService implements PageMetadataProvider {
    private final OrderRequestRepository orderRequestRepository;

    public List<OrderRequestDTO> getAll() {
        return orderRequestRepository.getAll();
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

}
