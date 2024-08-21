package com.ezentwix.teamcostco.repository;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;
import com.ezentwix.teamcostco.dto.ProductsDTO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class ProductsRepository {
    
    private final SqlSessionTemplate sql;

    public List<ProductsDTO> getAll() {
        return sql.selectList("Products.getAll");
    }

}
