package com.ezentwix.teamcostco.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.ezentwix.teamcostco.PageMetadataProvider;
import com.ezentwix.teamcostco.dto.product.ProductDefectiveDTO;
import com.ezentwix.teamcostco.pagination.PaginationRepository;
import com.ezentwix.teamcostco.pagination.PaginationResult;
import com.ezentwix.teamcostco.repository.ProductDefectiveRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ProductDefectiveService implements PageMetadataProvider {

    private final ProductDefectiveRepository productDefectiveRepository;
    private final PaginationRepository paginationRepository;

    public List<ProductDefectiveDTO> getAll() {
        return productDefectiveRepository.getAll();
    }

    public PaginationResult<ProductDefectiveDTO> getPage(String query, Integer page, Integer size, Map<String, Object> params) {
        return paginationRepository.getPage(
            query,
            "ProductDefective.getAll", 
            PageRequest.of(page, size), 
            params, 
            ProductDefectiveDTO.class);
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
