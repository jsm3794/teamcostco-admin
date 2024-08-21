package com.ezentwix.teamcostco.dto;

import lombok.Data;
import java.util.Date;

@Data
public class ProductsDTO {
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
    private Date create_date;
    private Date update_date;



}
