package com.ezentwix.teamcostco.dto.filter;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class InventoryFilterDTO {
    private List<FilterDTO> filterList = new ArrayList<FilterDTO>();

    int product_code;
    int purchase_price_start;
    int purchase_price_end;
    int selling_price_start;
    int selling_price_end;
    int category_large;
}
