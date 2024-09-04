package com.ezentwix.teamcostco.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ezentwix.teamcostco.dto.sales.SalesDataDTO;
import com.ezentwix.teamcostco.dto.sales.SalesQtyPracDTO;
import com.ezentwix.teamcostco.dto.sales.TotalSalesDTO;
import com.ezentwix.teamcostco.repository.SalesDataRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SalesDataService {

    private final SalesDataRepository salesDataRepository;

    public List<SalesDataDTO> getSalesData(String period) {
        LocalDate endDate = LocalDate.now();
        LocalDate startDate;

        switch (period.toLowerCase()) {
            case "week":
                startDate = endDate.minusWeeks(1);
                break;
            case "month":
                startDate = endDate.minusMonths(1);
                break;
            case "year":
                startDate = endDate.minusYears(1);
                break;
                
            default:
                startDate = endDate.minusWeeks(1);

        }

        return salesDataRepository.getSalesByDate(startDate.toString(), endDate.toString());

    }

    public List<TotalSalesDTO> getTotalSalesByPeriod() {
        return salesDataRepository.getTotalSalesByPeriod();
    }

    public List<SalesQtyPracDTO> selectWeeklyTopProducts() {
        return salesDataRepository.selectWeeklyTopProducts();
    }

    public Integer getTotalSales() {
        return salesDataRepository.getTotalSales();
    }

    public Integer getOperatingProfit() {
        return salesDataRepository.getOperatingProfit();
    }
}
