package com.ezentwix.teamcostco.dto.sales;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.Data;

@Data
public class SalesDataDTO {
    private Date salesDate;
    private BigInteger totalPrice;

    public String getFormattedSalesDate() {
        return new SimpleDateFormat("yyyy-MM-dd").format(salesDate);
    }

}
