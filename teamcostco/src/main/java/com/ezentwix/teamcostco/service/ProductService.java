package com.ezentwix.teamcostco.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.ezentwix.teamcostco.dto.product.ProductDTO;
import com.ezentwix.teamcostco.dto.product.ProductSummaryDTO;
import com.ezentwix.teamcostco.pagination.PaginationRepository;
import com.ezentwix.teamcostco.pagination.PaginationResult;
import com.ezentwix.teamcostco.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ProductService {
    private final PaginationRepository paginationRepository;
    private final ProductRepository productRepository;

    public List<ProductDTO> list() {

        return productRepository.getAll();
    }

    public PaginationResult<ProductDTO> getPage(Integer page, Integer limit, Map<String, Object> params) {
        return paginationRepository.getPage(
                "Products.getAll",
                PageRequest.of(page, limit),
                params,
                ProductDTO.class);
    }

    public ProductSummaryDTO eachProductCount(){
        ProductSummaryDTO psDTO = new ProductSummaryDTO();

        psDTO.setLowProducts(productRepository.getLowProducts());
        psDTO.setDefectedProducts(productRepository.getDefectedProducts());
        psDTO.setTotalProductsQty(productRepository.getTotalProductsQty());

        return psDTO;
    }

    public Integer getTotalProductsByUpdateDate() {
        return productRepository.getTotalProductsByUpdateDate();
    }

    public ProductDTO getProductById(Integer productId){
        return productRepository.findById(productId);
    }

    public void updateProduct(ProductDTO productDTO){
        productRepository.updateProduct(productDTO);
    }
}
