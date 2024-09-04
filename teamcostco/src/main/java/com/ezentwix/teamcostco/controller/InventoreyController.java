package com.ezentwix.teamcostco.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ezentwix.teamcostco.dto.filter.InventoryFilterDTO;
import com.ezentwix.teamcostco.dto.product.ProductDTO;
import com.ezentwix.teamcostco.dto.product.ProductSummaryDTO;
import com.ezentwix.teamcostco.pagination.PaginationResult;
import com.ezentwix.teamcostco.service.InventoryService;
import com.ezentwix.teamcostco.service.ProductService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class InventoreyController {
    private final InventoryService inventoryService;
    private final ProductService productService;
    private final ObjectMapper objectMapper;

    @GetMapping("/inventory")
    public String showInventory(
            @RequestParam(value = "query", defaultValue = "") String query,
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size,
            @ModelAttribute InventoryFilterDTO inventoryFilterDTO,
            Model model) {

        inventoryService.configureModel(model);

        Map<String, Object> map = objectMapper.convertValue(inventoryFilterDTO,
                new TypeReference<Map<String, Object>>() {
                });

        PaginationResult<ProductDTO> result = productService.getPage(query, page, size, map);
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
