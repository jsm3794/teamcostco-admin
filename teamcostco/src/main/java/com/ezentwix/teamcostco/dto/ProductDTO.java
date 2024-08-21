package com.ezentwix.teamcostco.dto;

import java.security.Timestamp;

import lombok.Data;

@Data
public class ProductDTO {
    private Integer product_id;
    private String product_name;
    private Integer product_code;
    private String category1;
    private String category2;
    private String category3;
    private String category4;
    private Integer purchase_price;
    private Integer selling_price;
    private String brand;
    private Integer appropriate_qty;
    private Integer storage_qty;
    private Integer display_qty;
    private Timestamp create_date;
    private Timestamp update_date;
}
