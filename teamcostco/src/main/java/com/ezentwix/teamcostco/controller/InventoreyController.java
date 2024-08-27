package com.ezentwix.teamcostco.controller;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ezentwix.teamcostco.dto.product.ProductDTO;
import com.ezentwix.teamcostco.dto.product.ProductSummaryDTO;
import com.ezentwix.teamcostco.service.InventoryService;
import com.ezentwix.teamcostco.service.ProductService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class InventoreyController {
    private final InventoryService inventoryService;
    private final ProductService productService;

    @GetMapping("/inventory")
    public String showInventory(@RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "3") int size,
            Model model) {
        inventoryService.configureModel(model);
        productService.configurePagingModel(model, page, size);
        return "index";
    }

    @GetMapping("/productsummary")
    @ResponseBody
    public ProductSummaryDTO getMethodName() {
        return productService.eachProductCount();
    }

}
