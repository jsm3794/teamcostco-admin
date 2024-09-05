package com.ezentwix.teamcostco.dto.product;

import java.util.Date;

import lombok.Data;

@Data
public class OrderRequestDTO {
    private Integer request_id;          
    private Long product_code;
    private String product_name;
    private String purchase_price;
    private String mall_name;
    private String mall_link;
    private Integer request_qty;
    private Integer received_qty;
    private Integer defective_qty;
    private Integer emp_id;
    private String emp_name;
    private String request_status;
    private Date create_date;
}
