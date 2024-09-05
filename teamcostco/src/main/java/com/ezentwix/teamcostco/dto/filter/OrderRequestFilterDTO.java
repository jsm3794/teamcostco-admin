package com.ezentwix.teamcostco.dto.filter;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class OrderRequestFilterDTO {
    private List<FilterDTO> filterList = new ArrayList<FilterDTO>();

    int order_number;
    String mall_name;
    String order_date_start;
    String order_date_end;
    String request_status;
    String emp_name;
}