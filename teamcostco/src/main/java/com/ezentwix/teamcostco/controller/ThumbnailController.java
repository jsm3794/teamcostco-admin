package com.ezentwix.teamcostco.controller;

import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ezentwix.teamcostco.dto.product.ProductThumbnailDTO;
import com.ezentwix.teamcostco.service.ThumbnailService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/thumbnails")
public class ThumbnailController {

    private final ThumbnailService thumbnailService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadThumbnail(@RequestParam("productImageFile") MultipartFile file,
                                                   @RequestParam("productCode") Integer productCode) {
        try {
            ProductThumbnailDTO dto = new ProductThumbnailDTO();
            dto.setProductImageFile(file);
            dto.setProductCode(productCode);
            dto.setCreateDate(LocalDateTime.now());
            dto.setUpdateDate(LocalDateTime.now());
            thumbnailService.upload(dto);

            return ResponseEntity.ok("파일 업로드 성공");
        } catch (IOException | IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("파일 업로드 실패: " + e.getMessage());
        }
    }
}
