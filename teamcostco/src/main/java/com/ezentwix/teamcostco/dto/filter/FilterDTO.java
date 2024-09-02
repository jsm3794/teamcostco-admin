package com.ezentwix.teamcostco.dto.filter;

import java.util.Map;

import lombok.Data;

@Data
public class FilterDTO {
    String filter_title;
    String tag;
    String type;
    String name;
    String placeholder;
    Map<String, Object> data;

    public FilterDTO(String filter_title, String tag, String type, String name, String placeholder,
            Map<String, Object> data) {
        this.filter_title = filter_title;
        this.tag = tag;
        this.type = type;
        this.name = name;
        this.placeholder = placeholder;
        this.data = data;
    }

}
