package com.ezentwix.teamcostco.service;

import org.springframework.stereotype.Service;

import com.ezentwix.teamcostco.PageMetadataProvider;
import com.ezentwix.teamcostco.dto.sales.SalesDTO;
import com.ezentwix.teamcostco.repository.SalesDetailRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SalesDetailService implements PageMetadataProvider{
    
    private final SalesDetailRepository salesDetailRepository;

    public SalesDTO get(Integer sales_id) {
        return salesDetailRepository.get(sales_id);
    }

    @Override
    public String getUri() {

        return "sales/salesDetail";
    }

    @Override
    public String getPageTitle() {
        
        return "세부정보";
    }
}
