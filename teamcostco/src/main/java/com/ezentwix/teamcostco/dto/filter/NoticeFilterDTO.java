package com.ezentwix.teamcostco.dto.filter;

import java.util.ArrayList;
import java.util.List;

import org.threeten.bp.format.DateTimeFormatter;

import lombok.Data;

@Data
public class NoticeFilterDTO {

    private List<FilterDTO> filterList = new ArrayList<FilterDTO>();
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    String job_title;
    String write_date_start;
    String write_date_end;
}