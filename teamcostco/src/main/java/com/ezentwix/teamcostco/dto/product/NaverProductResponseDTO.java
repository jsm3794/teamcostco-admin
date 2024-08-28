package com.ezentwix.teamcostco.dto.product;

import java.util.List;

import lombok.Data;

@Data
public class NaverProductResponseDTO {
private String lastBuildDate;
    private int total;
    private int start;
    private int display;
    private List<NaverProductDTO> items;
}
