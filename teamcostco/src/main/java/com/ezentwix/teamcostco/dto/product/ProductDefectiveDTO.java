package com.ezentwix.teamcostco.dto.product;

import java.util.Date;

import lombok.Data;

@Data
public class ProductDefectiveDTO {

    private Integer defective_id;
    private Long product_code;
    private String product_name;
    private Integer defective_qty;
    private String reason;
    private Date defective_date;
}
