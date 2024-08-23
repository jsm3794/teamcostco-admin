package com.ezentwix.teamcostco.dto;

import java.security.Timestamp;

import lombok.Data;

@Data
public class EmployeeDTO {
    private Integer emp_id;
    private String emp_name;
    private String emp_email;
    private String phone_number;
    private String login_id;
    private String login_pw;
    private String job_title;
    private Timestamp hire_date;
    private Timestamp join_date;
}