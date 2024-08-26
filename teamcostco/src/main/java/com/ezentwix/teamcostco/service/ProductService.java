package com.ezentwix.teamcostco.service;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ezentwix.teamcostco.dto.product.ProductDTO;
import com.ezentwix.teamcostco.dto.product.ProductSummaryDTO;
import com.ezentwix.teamcostco.repository.ProductRepository;

import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@Service
public class ProductService {
    private final ProductRepository productRepository;
    public List<ProductDTO> list() {
        return productRepository.getAll();
    }
    //
    public Page<ProductDTO> getPagedProducts(int page, int size) {
        int start = page * size;
        int end = start + size;
        List<ProductDTO> products = productRepository.findAllProducts(start, end);
        int total = productRepository.getTotalProductsQty();
        Pageable pageable = PageRequest.of(page, size);
        return new PageImpl<>(products, pageable, total);
    }
    public ProductSummaryDTO eachProductCount() {
        ProductSummaryDTO productSummary = new ProductSummaryDTO();
        productSummary.setTotalCategories(productRepository.getTotalCategories());
        productSummary.setTotalProductsQty(productRepository.getTotalProductsQty());
        productSummary.setLowProducts(productRepository.getLowProducts());
        return productSummary;
    }
}