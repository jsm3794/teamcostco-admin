package com.ezentwix.teamcostco.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

// large , medium 카테고리DTO

@Data
public class CategoryDTO {
    @JsonProperty("catId")
    private String catId;

    @JsonProperty("catNm")
    private String catNm;

    @JsonProperty("catLvl")
    private int catLvl;

    @JsonProperty("isLeaf")
    private boolean isLeaf;

    @JsonProperty("order")
    private int order;

    @JsonProperty("catImg1")
    private String catImg1;

    @JsonProperty("catImg2")
    private String catImg2;

    @JsonProperty("catImg3")
    private String catImg3;

    @JsonProperty("mobileLink")
    private String mobileLink;

    @JsonProperty("pcLink")
    private String pcLink;

    @JsonProperty("categories")
    private List<CategoryDTO> categories;
}
