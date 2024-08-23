package com.ezentwix.teamcostco.service.dashboard;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.ezentwix.teamcostco.dto.CategoryDTO;
import com.ezentwix.teamcostco.dto.DetailCategoryDTO;
import com.ezentwix.teamcostco.dto.SmallCategoryDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DashBoardService {

    private static final String API_URL = "https://shopping.naver.com/api/modules/gnb/category?id=root&_vc_=1723553095043";

    private final JdbcTemplate jdbcTemplate;

    public void configureDashboardData(Model model) {
        model.addAttribute("uri", "dashboard/dashboard");
        model.addAttribute("pageTitle", "대시보드");

        model.addAttribute("cssFiles",
                List.of("/css/dashboard/styles.css"));

        try {
            // 네이버 쇼핑몰 대,중,소,세분류 카테고리 불러오는 기능
            // List<CategoryDTO> categoryDTOList = fetchCategoryDataFromApi(API_URL);
            // saveCategoryDTOs(categoryDTOList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List<CategoryDTO> fetchCategoryDataFromApi(String apiUrl) throws Exception {
        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "*/*");
        connection.setRequestProperty("Accept-Charset", "UTF-8");
        connection.setRequestProperty("User-Agent",
                "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36");

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            String contentType = connection.getContentType();
            String charset = "UTF-8";

            if (contentType != null && contentType.contains("charset=")) {
                charset = contentType.substring(contentType.indexOf("charset=") + 8);
            }

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), charset));
            StringBuilder response = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(response.toString(),
                    objectMapper.getTypeFactory().constructCollectionType(List.class, CategoryDTO.class));
        } else {
            throw new RuntimeException("GET request failed. Response Code: " + responseCode);
        }
    }

    private List<DetailCategoryDTO> fetchDetailCategoryDataFromApi(String apiUrl) throws Exception {
        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "*/*");
        connection.setRequestProperty("Accept-Charset", "UTF-8");
        connection.setRequestProperty("User-Agent",
                "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36");

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            String contentType = connection.getContentType();
            String charset = "UTF-8";

            if (contentType != null && contentType.contains("charset=")) {
                charset = contentType.substring(contentType.indexOf("charset=") + 8);
            }

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), charset));
            StringBuilder response = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(response.toString(),
                    objectMapper.getTypeFactory().constructCollectionType(List.class, DetailCategoryDTO.class));
        } else {
            throw new RuntimeException("GET request failed. Response Code: " + responseCode);
        }
    }

    private List<SmallCategoryDTO> fetchSmallCategoryDataFromApi(String apiUrl) throws Exception {
        
        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "*/*");
        connection.setRequestProperty("Accept-Charset", "UTF-8");
        connection.setRequestProperty("User-Agent",
                "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36");

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            String contentType = connection.getContentType();
            String charset = "UTF-8";

            if (contentType != null && contentType.contains("charset=")) {
                charset = contentType.substring(contentType.indexOf("charset=") + 8);
            }

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), charset));
            StringBuilder response = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(response.toString(),
                    objectMapper.getTypeFactory().constructCollectionType(List.class, SmallCategoryDTO.class));
        } else {
            throw new RuntimeException("GET request failed. Response Code: " + responseCode);
        }
    }

    private void saveSmallCategoryDTOs(List<SmallCategoryDTO> smallCategoryDTOList, String mediumCategoryId) {
        String sqlLevel3 = "INSERT INTO category_small (small_id, small_name, medium_id) VALUES(?, ?, ?)";

        try {
            for (SmallCategoryDTO smallCategory : smallCategoryDTOList) {
                jdbcTemplate.update(sqlLevel3, smallCategory.getCatId(), smallCategory.getCatNm(), mediumCategoryId);

                String detailCategoryApiUrl = "https://shopping.naver.com/api/modules/gnb/category?id=" + smallCategory.getCatId();
                        try {
                            List<DetailCategoryDTO> detailCategoryDTOList = fetchDetailCategoryDataFromApi(detailCategoryApiUrl);
                            saveDetailCategoryDTOs(detailCategoryDTOList , smallCategory.getCatId());
                            
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void saveDetailCategoryDTOs(List<DetailCategoryDTO> detailCategoryDTOList, String smallCategoryId) {
        String sqlLevel4 = "INSERT INTO category_detail (detail_id, detail_name, small_id) VALUES(?, ?, ?)";

        for (DetailCategoryDTO detailCategory : detailCategoryDTOList) {
            jdbcTemplate.update(sqlLevel4, detailCategory.getCatId(), detailCategory.getCatNm(), smallCategoryId);
        }
    }

    private void saveCategoryDTOs(List<CategoryDTO> categoryDTOList) {
        jdbcTemplate.update("DELETE FROM category_large");
        jdbcTemplate.update("DELETE FROM category_medium");
        jdbcTemplate.update("DELETE FROM category_small");
        jdbcTemplate.update("DELETE FROM category_detail");

        String sqlLevel1 = "INSERT INTO category_large (large_id, large_name) VALUES (?, ?)";
        String sqlLevel2 = "INSERT INTO category_medium (medium_id, medium_name, large_id) VALUES (?, ?, ?)";

        for (CategoryDTO categoryDTO : categoryDTOList) {
            if (categoryDTO.getCatLvl() == 1) {
                jdbcTemplate.update(sqlLevel1, categoryDTO.getCatId(), categoryDTO.getCatNm());

                // 하위 카테고리 처리 (대분류 안에 중분류가 들어 있음)
                if (categoryDTO.getCategories() != null) {
                    for (CategoryDTO subCategory : categoryDTO.getCategories()) {
                        jdbcTemplate.update(sqlLevel2, subCategory.getCatId(), subCategory.getCatNm(), categoryDTO.getCatId());

                        // 소카테고리 데이터 가져오기
                        String subCategoryApiUrl = "https://shopping.naver.com/api/modules/gnb/category?id=" + subCategory.getCatId();
                        try {
                            List<SmallCategoryDTO> smallCategoryDTOList = fetchSmallCategoryDataFromApi(subCategoryApiUrl);
                            saveSmallCategoryDTOs(smallCategoryDTOList, subCategory.getCatId());
                            
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        
                    }
                }
            }
        }
    }
}
//https://shopping.naver.com/api/modules/gnb/category?id=100011454

// https://search.shopping.naver.com/search/category/100011431   제품 검색