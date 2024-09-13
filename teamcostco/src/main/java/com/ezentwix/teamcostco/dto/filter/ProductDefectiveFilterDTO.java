package com.ezentwix.teamcostco.dto.filter;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class ProductDefectiveFilterDTO {
    private List<FilterDTO> filterList = new ArrayList<FilterDTO>();

    String product_name;
    String reason;
    String defective_date_start;
    String defective_date_end;

}
