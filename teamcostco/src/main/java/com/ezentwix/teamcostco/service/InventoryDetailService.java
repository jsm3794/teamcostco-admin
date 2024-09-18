package com.ezentwix.teamcostco.service;

import org.springframework.stereotype.Service;
import com.ezentwix.teamcostco.PageMetadataProvider;
import lombok.RequiredArgsConstructor;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryDetailService implements PageMetadataProvider {

    @Override
    public String getUri() {
        return "/inventory/detail"; 
    }

    @Override
    public String getPageTitle() {
        return "재고관리";
    }

    @Override
    public List<String> getCssFiles() {
        return List.of("/css/contents/inventorydetail.css");
    }

    @Override
    public List<String> getJsFiles() {
        return List.of("/js/contents/inventorydetail.js");
    }
}