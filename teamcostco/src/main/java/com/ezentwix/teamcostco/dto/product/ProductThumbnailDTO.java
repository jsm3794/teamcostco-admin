package com.ezentwix.teamcostco.dto.product;

import java.util.Date;

import lombok.Data;

@Data
public class ProductThumbnailDTO {
    private Integer thumbnailId;
    private Integer productCode;
    private String thumbnailUrl;
    private Date createDate;
    private Date updateDate;
}
