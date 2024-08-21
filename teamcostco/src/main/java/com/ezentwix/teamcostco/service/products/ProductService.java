package com.ezentwix.teamcostco.service.products;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ezentwix.teamcostco.dto.ProductDTO;
import com.ezentwix.teamcostco.repository.ProductRepository;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;

    public List<ProductDTO> list() {

        return productRepository.getAll();
    }

}
