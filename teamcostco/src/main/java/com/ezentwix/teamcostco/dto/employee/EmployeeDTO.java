package com.ezentwix.teamcostco.dto.employee;

import java.time.LocalDate;

import com.google.type.Date;

import lombok.Data;

@Data
public class EmployeeDTO {
    private Integer emp_id;
    private String emp_name;
    private String oldEmpName;
    private String emp_email;
    private String oldEmpEmail;
    private String phone_number;
    private String oldPhoneNumber;
    private String login_id;
    private String oldLoginId;
    private String login_pw;
    private String oldLoginPw;
    private String job_title;
    private String oldJobTitle;
    private LocalDate birthday;
    private LocalDate oldBirthday;
    private String gender;
    private String oldGender;
    private String address;
    private String oldAddress;
    private String detail_address;
    private String oldDetailAddress;
    private Date hire_date;
    private Date join_date;
    private String setEmailVerificationToken;
    private String emailVerified;
}