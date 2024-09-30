package com.ezentwix.teamcostco.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ezentwix.teamcostco.PageMetadataProvider;
import com.ezentwix.teamcostco.dto.customer.CustomerDTO;
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

    public String getURL(String product_code) {
        return salesDetailRepository.getURL(product_code);
    }

    public CustomerDTO getCustomerInfo(String social_id) {
        return salesDetailRepository.getCustomerInfo(social_id);
    }

    @Override
    public String getUri() {

        return "sales/salesDetail";
    }

    @Override
    public String getPageTitle() {
        
        return "세부정보";
    }

    @Override
    public List<String> getCssFiles() {
        return List.of("/css/contents/salesdetail.css");
    }
}
