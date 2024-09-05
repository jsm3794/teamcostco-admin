package com.ezentwix.teamcostco.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ezentwix.teamcostco.PageMetadataProvider;
import com.ezentwix.teamcostco.repository.OrderRequestRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderRequestDetailService implements PageMetadataProvider {
    private final OrderRequestRepository orderRequestRepository;

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

    
}
