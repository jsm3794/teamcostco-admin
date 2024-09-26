package com.ezentwix.teamcostco.dto.sales;

import java.util.Date;

import lombok.Data;

@Data
public class SalesDTO {
    private Integer sales_item_id;
    private String product_name;
    private Integer customer_id;
    private Integer qty;
    private Integer total_price;
    private Date sale_date;
    private Integer sales_id;
    private String payments_type;
    private String sales_status;
}
