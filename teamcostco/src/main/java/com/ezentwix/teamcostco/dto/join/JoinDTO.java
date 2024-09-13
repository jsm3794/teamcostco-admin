package com.ezentwix.teamcostco.dto.join;

import java.time.LocalDate;

import lombok.Data;

@Data
public class JoinDTO {
    private String emp_name;
    private String emp_email;
    private String phone_number;
    private String login_id;
    private String login_pw;
    private String login_Pw_Check;
    private String job_title;
    private LocalDate birthday;
    private String gender;
    private String address;
    private String detail_address;
    private String post_number;

    private String emailVerificationToken; // 이메일 인증 토큰
    private boolean emailVerified; // 이메일 인증 여부
}
