package com.ezentwix.teamcostco.service;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.ezentwix.teamcostco.dto.product.ProductDTO;
import com.ezentwix.teamcostco.dto.product.ProductSummaryDTO;
import com.ezentwix.teamcostco.pagination.PagingModule;
import com.ezentwix.teamcostco.pagination.PagingRepositoryInterface;
import com.ezentwix.teamcostco.repository.ProductRepository;

import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@Service
public class ProductService extends PagingModule<ProductDTO> {
    private final ProductRepository productRepository;

    // 페이징 관련
    @Override
    protected PagingRepositoryInterface<ProductDTO> getPagingRepository() {
        return productRepository;
    }
    
    public List<ProductDTO> list() {
        return productRepository.getAll();
    }


    public ProductSummaryDTO eachProductCount() {
        ProductSummaryDTO productSummary = new ProductSummaryDTO();
        productSummary.setTotalCategories(productRepository.getTotalCategories());
        productSummary.setTotalProductsQty(productRepository.getTotalProductsQty());
        productSummary.setLowProducts(productRepository.getLowProducts());
        return productSummary;
    }

     // 제품 ID로 제품을 가져오는 메서드 추가
     public ProductDTO getProductById(Integer productId) {
        return productRepository.findById(productId);
    }
    
}