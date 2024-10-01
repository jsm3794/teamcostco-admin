package com.ezentwix.teamcostco.service;

import org.springframework.stereotype.Service;
import com.ezentwix.teamcostco.PageMetadataProvider;
import lombok.RequiredArgsConstructor;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryModifyService implements PageMetadataProvider {

    @Override
    public String getUri() {
        return "inventory/modify"; 
    }

    @Override
    public String getPageTitle() {
        return "재고관리";
    }

    @Override
    public List<String> getCssFiles() {
        return List.of("/css/contents/inventorymodify.css");
    }

    @Override
    public List<String> getJsFiles() {
        return List.of("/js/contents/inventorymodify.js");
    }
}
