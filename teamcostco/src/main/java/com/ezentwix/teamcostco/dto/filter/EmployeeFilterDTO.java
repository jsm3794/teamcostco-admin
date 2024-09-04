package com.ezentwix.teamcostco.dto.filter;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class EmployeeFilterDTO {
    private List<FilterDTO> filterList = new ArrayList<FilterDTO>();

    String emp_email;
    String phone_number;
    String job_title;
    String hire_date_start;
    String hire_date_end;
    String join_date_start;
    String join_date_end;
}