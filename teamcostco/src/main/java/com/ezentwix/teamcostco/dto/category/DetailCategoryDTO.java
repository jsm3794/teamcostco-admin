package com.ezentwix.teamcostco.dto.category;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class DetailCategoryDTO {

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

    @JsonProperty("mobileLink")
    private String mobileLink;

    @JsonProperty("pcLink")
    private String pcLink;
}


