package com.ezentwix.teamcostco.service;

import org.springframework.stereotype.Service;
import com.ezentwix.teamcostco.PageMetadataProvider;
import lombok.RequiredArgsConstructor;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DetailService implements PageMetadataProvider {

    @Override
    public String getUri() {
        return "inventory/{productId}"; 
    }

    @Override
    public String getPageTitle() {
        return "상품 상세정보";
    }

    @Override
    public List<String> getCssFiles() {
        return List.of("/css/contents/detail.css");
    }

    @Override
    public List<String> getJsFiles() {
        return List.of("/js/contents/inventory.js");
    }
}
