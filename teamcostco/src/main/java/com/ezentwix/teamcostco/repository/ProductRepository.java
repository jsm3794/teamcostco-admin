package com.ezentwix.teamcostco.repository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;
import com.ezentwix.teamcostco.dto.product.ProductDTO;
import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@Repository
public class ProductRepository {
    private final SqlSessionTemplate sql;
    public List<ProductDTO> getAll() {
        return sql.selectList("Product.getAll");
    }
    //
    public List<ProductDTO> findAllProducts(int start, int end) {
        Map<String, Integer> params = new HashMap<>();
        params.put("start", start);
        params.put("end", end);
        return sql.selectList("Product.findAllProducts", params);
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
}