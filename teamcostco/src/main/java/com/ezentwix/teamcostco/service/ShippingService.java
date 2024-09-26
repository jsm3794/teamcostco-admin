package com.ezentwix.teamcostco.service;

import java.util.Map;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.ezentwix.teamcostco.PageMetadataProvider;
import com.ezentwix.teamcostco.dto.shipping.ShippingDTO;
import com.ezentwix.teamcostco.pagination.PaginationRepository;
import com.ezentwix.teamcostco.pagination.PaginationResult;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ShippingService implements PageMetadataProvider{

    private final PaginationRepository paginationRepository;
    
    public PaginationResult<ShippingDTO> getPage(String query, int page, int size, Map<String, Object> params) {
        return paginationRepository.getPage(query, "Shipping.getAll", PageRequest.of(page, size), params,
                ShippingDTO.class);
    }

    @Override
    public String getUri() {
        return "shipping/shipping";
    }

    @Override
    public String getPageTitle() {
        return "배송현황";
    }

}
