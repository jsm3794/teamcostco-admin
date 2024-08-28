package com.ezentwix.teamcostco.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.ezentwix.teamcostco.dto.sales.SalesDataDTO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class SalesDataRepository {

    private final SqlSessionTemplate sql;

    public List<SalesDataDTO> getSalesByDate(String startDate, String endDate) {
        Map<String, Object> params = new HashMap<>();

        params.put("startDate", startDate);
        params.put("endDate", endDate);
        return sql.selectList("Sales.salesByDate", params);
    }

}
