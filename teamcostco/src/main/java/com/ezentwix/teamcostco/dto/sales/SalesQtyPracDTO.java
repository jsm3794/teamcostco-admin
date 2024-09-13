package com.ezentwix.teamcostco.dto.sales;

import java.time.LocalDate;

import lombok.Data;

@Data
public class SalesQtyPracDTO {

    String sold_product_name;
    Integer sold_qty;
    LocalDate sold_date;

}
