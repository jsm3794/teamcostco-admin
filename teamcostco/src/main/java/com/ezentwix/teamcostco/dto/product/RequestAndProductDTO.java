package com.ezentwix.teamcostco.dto.product;

import lombok.Data;

@Data
public class RequestAndProductDTO {
    Integer request_id;
    String product_name;
    Integer request_qty;
}
