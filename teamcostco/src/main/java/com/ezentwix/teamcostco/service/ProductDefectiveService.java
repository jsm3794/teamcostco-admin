package com.ezentwix.teamcostco.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ezentwix.teamcostco.PageMetadataProvider;
import com.ezentwix.teamcostco.dto.product.ProductDefectiveDTO;
import com.ezentwix.teamcostco.repository.ProductDefectiveRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ProductDefectiveService implements PageMetadataProvider {

    private final ProductDefectiveRepository productDefectiveRepository;

    public List<ProductDefectiveDTO> getAll() {
        return productDefectiveRepository.getAll();
    }

    @Override
    public List<String> getCssFiles() {
        return List.of("/css/contents/orderrequest.css");
    }

    @Override
    public String getUri() {
        return "productdefective/productdefective";
    }
    
    @Override
    public String getPageTitle() {
        return "불량재고";        
    }
}
