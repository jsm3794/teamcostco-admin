package com.ezentwix.teamcostco.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ezentwix.teamcostco.dto.product.ProductDTO;
import com.ezentwix.teamcostco.dto.product.ProductSummaryDTO;
import com.ezentwix.teamcostco.pagination.PaginationResult;
import com.ezentwix.teamcostco.service.InventoryService;
import com.ezentwix.teamcostco.service.ProductService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class InventoreyController {
    private final InventoryService inventoryService;
    private final ProductService productService;

    @GetMapping("/inventory")
    public String showInventory(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "size", defaultValue = "3") Integer size,
            Model model) {

        inventoryService.configureModel(model);

        PaginationResult<ProductDTO> result = productService.getPage(page, size, Map.of());
        model.addAttribute("items", result.getData());
        model.addAttribute("count", result.getCount());
        model.addAttribute("pageDetail", result.getPageDetails());
        return "index";
    }

    @GetMapping("/productsummary")
    @ResponseBody
    public ProductSummaryDTO getMethodName() {
        return productService.eachProductCount();
    }

}
