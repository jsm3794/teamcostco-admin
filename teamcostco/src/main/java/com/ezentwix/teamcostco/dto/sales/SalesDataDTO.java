package com.ezentwix.teamcostco.dto.sales;

import java.math.BigInteger;
import java.time.LocalDate;

import lombok.Data;

@Data
public class SalesDataDTO {
    private LocalDate salesDate;
    private BigInteger totalPrice;

    // public String getFormattedSalesDate() {
    //     return new SimpleDateFormat("yyyy-MM-dd").format(salesDate);
    // }

}
