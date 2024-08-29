package com.ezentwix.teamcostco.repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.ezentwix.teamcostco.dto.product.ProductDefectiveDTO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class ProductDefectiveRepository {

    private final SqlSessionTemplate sql;

    public List<ProductDefectiveDTO> getAll() {
        return sql.selectList("ProductDefective.getAll");
    }
}
