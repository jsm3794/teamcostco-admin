package com.ezentwix.teamcostco.dto.notice;

import java.util.Date;

import lombok.Data;

@Data
public class BoardDTO {

    private Integer notice_id;
    private String title;
    private String content;
    private Integer emp_id;
    private String job_title;
    private Date create_date;
    private Date update_date;
}
