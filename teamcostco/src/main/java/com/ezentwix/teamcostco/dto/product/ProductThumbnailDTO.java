package com.ezentwix.teamcostco.dto.product;

import java.time.LocalDateTime;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class ProductThumbnailDTO {
    private Integer thumbnailId;
    private Long productCode;
    private String thumbnailUrl;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    
    private MultipartFile productImageFile; // 파일 업로드용
}
 