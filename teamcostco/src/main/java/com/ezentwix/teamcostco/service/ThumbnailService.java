package com.ezentwix.teamcostco.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.ezentwix.teamcostco.dto.product.ProductThumbnailDTO;
import com.ezentwix.teamcostco.repository.ThumbnailsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@Service
public class ThumbnailService {

    private final AmazonS3 amazonS3;

    @Value("${cloud.aws.s3.bucket}")
    private String bucketName;

    private final ThumbnailsRepository thumbnailsRepository;

    public void upload(ProductThumbnailDTO productThumbnailDTO) throws IOException {
        MultipartFile productImageFile = productThumbnailDTO.getProductImageFile();

        if (productImageFile == null || productImageFile.isEmpty()) {
            throw new IllegalArgumentException("File cannot be null or empty");
        }

        String fileName = productThumbnailDTO.getProductCode() + ".jpg";

        // S3 버킷에 동일한 파일명이 존재하는지 확인
        if (amazonS3.doesObjectExist(bucketName, fileName)) {
            log.info("**** 이미 파일이 존재합니다. 업로드를 중단합니다. ****");
            return;  // 이미 존재하면 메서드 종료
        }

        // 파일 업로드
        amazonS3.putObject(new PutObjectRequest(bucketName, fileName, productImageFile.getInputStream(), null));

        // 업로드된 파일의 URL 가져오기
        String fileUrl = amazonS3.getUrl(bucketName, fileName).toString();

        productThumbnailDTO.setThumbnailUrl(fileUrl);

        // DB에 삽입
        thumbnailsRepository.insertProductThumbnails(productThumbnailDTO);
    }
}
