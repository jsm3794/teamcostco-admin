package com.ezentwix.teamcostco.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ezentwix.teamcostco.PageMetadataProvider;
import com.ezentwix.teamcostco.dto.product.RequestAndProductDTO;
import com.ezentwix.teamcostco.repository.OrderRequestRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DashBoardService implements PageMetadataProvider {

    private final OrderRequestRepository orderRequestreRepository;

    @Override
    public String getUri() {
        return "dashboard/dashboard";
    }

    @Override
    public String getPageTitle() {
        return "대시보드";
    }

    // 아래 두 메서드(getCssFiles, getJsFiles)는
    // Override 메서드를 구현하지않으면 null을 반환하도록 설계되어있음

    @Override
    public List<String> getCssFiles() {
        return List.of("/css/contents/dashboard.css");
    }

    @Override
    public List<String> getJsFiles() {
        return List.of(
                "https://cdn.jsdelivr.net/npm/chart.js",
                "https://cdn.jsdelivr.net/npm/date-fns@2.29.3/index.min.js",
                "https://cdn.jsdelivr.net/npm/chartjs-adapter-date-fns",
                "/js/contents/dashboard.js",
                "https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.1/moment.min.js");
    }
    
    public List<RequestAndProductDTO> getRequestAndProductInfo() {
        return orderRequestreRepository.getRequestAndProductInfo();
    }

}