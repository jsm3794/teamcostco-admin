package com.ezentwix.teamcostco.dto.sales;

import java.util.Date;

import lombok.Data;

@Data
public class TotalSalesDTO {
    private Date sales_date;
    private Integer total_sales;
}
