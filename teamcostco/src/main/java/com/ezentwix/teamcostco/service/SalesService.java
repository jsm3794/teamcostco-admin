package com.ezentwix.teamcostco.service;

import java.util.Map;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.ezentwix.teamcostco.PageMetadataProvider;
import com.ezentwix.teamcostco.dto.sales.SalesDTO;
import com.ezentwix.teamcostco.pagination.PaginationRepository;
import com.ezentwix.teamcostco.pagination.PaginationResult;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SalesService implements PageMetadataProvider {
    private final PaginationRepository paginationRepository;

    public PaginationResult<SalesDTO> getPage(String query, int page, int size, Map<String, Object> params) {
        return paginationRepository.getPage(query, "Sales.getAll", PageRequest.of(page, size), params,
                SalesDTO.class);
    }

    @Override
    public String getUri() {

        return "sales/sales";
    }

    @Override
    public String getPageTitle() {
        return "판매내역";
    }

}
