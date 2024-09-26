package com.ezentwix.teamcostco.repository;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.ezentwix.teamcostco.dto.sales.SalesDTO;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class SalesDetailRepository {
    
    private final SqlSessionTemplate sql;

    public SalesDTO get(Integer sales_id) {
        return sql.selectOne("Sales.get", sales_id);
    }
}
