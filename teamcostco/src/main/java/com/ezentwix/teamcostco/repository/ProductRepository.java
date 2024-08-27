package com.ezentwix.teamcostco.repository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;
import com.ezentwix.teamcostco.dto.product.ProductDTO;
import com.ezentwix.teamcostco.pagination.PagingRepositoryInterface;

import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@Repository
public class ProductRepository implements PagingRepositoryInterface<ProductDTO>{
    private final SqlSessionTemplate sql;
    public List<ProductDTO> getAll() {
        return sql.selectList("Product.getAll");
    }

    public Integer getTotalCategories() {
        return sql.selectOne("Product.getTotalCategories");
    }
    public Integer getTotalProductsQty() {
        return sql.selectOne("Product.getTotalProductsQty");
    }
    public Integer getLowProducts() {
        return sql.selectOne("Product.getLowProducts");
    }

     // 제품 ID로 제품을 가져오는 메서드 추가
     public ProductDTO findById(Integer productId) {
        return sql.selectOne("Product.findById", productId);
    }

    // ---------------------- 페이징 관련
    @Override
    public List<ProductDTO> findTableItems(int start, int end) {
        Map<String, Integer> params = new HashMap<>();
        params.put("start", start);
        params.put("end", end);
        return sql.selectList("Page.findTableItems", params);
    }

    // 위의 getTotalProductsQty와 겹침
    @Override
    public Integer countTableItems() {
        return sql.selectOne("Page.countTableItems");
    }
}