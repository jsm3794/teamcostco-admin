package com.ezentwix.teamcostco.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.ezentwix.teamcostco.dto.sales.SalesDataDTO;
import com.ezentwix.teamcostco.dto.sales.SalesQtyPracDTO;
import com.ezentwix.teamcostco.dto.sales.TotalSalesDTO;

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

    public List<TotalSalesDTO> getTotalSalesByPeriod() {
        return sql.selectList("Sales.totalSalesByPeriod");
    }

    public List<SalesQtyPracDTO> selectWeeklyTopProducts() {
        return sql.selectList("Sales.selectWeeklyTopProducts");
    }

    public Integer getTotalSales() {
        return sql.selectOne("Sales.getTotalSales");
    }

    public Integer getOperatingProfit() {
        return sql.selectOne("Sales.getOperatingProfit");
    }
}
