package com.ezentwix.teamcostco.dto;

import java.security.Timestamp;

import lombok.Data;

@Data
public class ProductDTO {
    private Integer product_id;
    private String product_name;
    private Integer product_code;
    private String category_large;
    private String category_medium;
    private String category_small;
    private String category_detail;
    private Integer purchase_price;
    private Integer selling_price;
    private String brand;
    private Integer appropriate_qty;
    private Integer storage_qty;
    private Integer display_qty;
    private Integer total_qty;
    private Timestamp create_date;
    private Timestamp update_date;
}
