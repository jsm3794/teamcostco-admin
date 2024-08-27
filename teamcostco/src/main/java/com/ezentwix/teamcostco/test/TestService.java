package com.ezentwix.teamcostco.test;

import org.springframework.stereotype.Service;

import com.ezentwix.teamcostco.dto.product.ProductDTO;
import com.ezentwix.teamcostco.pagination.PagingModule;
import com.ezentwix.teamcostco.pagination.PagingRepositoryInterface;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TestService extends PagingModule<ProductDTO>{

    private final TestRepository repository;

    @Override
    protected PagingRepositoryInterface<ProductDTO> getPagingRepository() {
        return repository;
    }
}
