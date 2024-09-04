package com.ezentwix.teamcostco.dto.sales;

import java.time.LocalDate;

import lombok.Data;

@Data
public class TotalSalesDTO {
    private LocalDate sales_date;
    private Integer total_sales;
}
