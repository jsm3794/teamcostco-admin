package com.ezentwix.teamcostco.repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.ezentwix.teamcostco.dto.sales.SalesDTO;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class SalesRepository {
    
    private final SqlSessionTemplate sql;

    public List<SalesDTO> getAll() {
        return sql.selectList("Sales.getAll");
    }

    
}
