package com.ezentwix.teamcostco.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ezentwix.teamcostco.dto.filter.InventoryFilterDTO;
import com.ezentwix.teamcostco.dto.product.ProductDTO;
import com.ezentwix.teamcostco.dto.product.ProductSummaryDTO;
import com.ezentwix.teamcostco.pagination.PaginationResult;
import com.ezentwix.teamcostco.service.InventoryDetailService;
import com.ezentwix.teamcostco.service.InventoryModifyService;
import com.ezentwix.teamcostco.service.InventoryService;
import com.ezentwix.teamcostco.service.ProductService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/inventory")
public class InventoreyController {
    private final InventoryService inventoryService;
    private final InventoryDetailService inventoryDetailService;
    private final InventoryModifyService inventoryModifyService;
    private final ProductService productService;
    private final ObjectMapper objectMapper;

    @GetMapping("")
    public String showInventory(
            @RequestParam(value = "query", defaultValue = "") String query,
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "size", defaultValue = "15") Integer size,
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

    @GetMapping("/detail/{productId}")
    public String showInventoryDetail(@PathVariable Integer productId, Model model) {
        ProductDTO product = productService.getProductById(productId);
        inventoryDetailService.configureModel(model);
        model.addAttribute("product", product);
        return "index";
    }

    @GetMapping("/modify/{productId}")
    public String showInventoryModify(@PathVariable Integer productId, Model model) {
        ProductDTO product = productService.getProductById(productId);
        inventoryModifyService.configureModel(model);
        model.addAttribute("product", product);
        return "index";
    }

    @GetMapping("/productsummary")
    @ResponseBody
    public ProductSummaryDTO getMethodName() {
        return productService.eachProductCount();
    }

    @PostMapping("/update")
    @ResponseBody
    public Map<String, Object> updateProduct(@ModelAttribute("product") ProductDTO productDTO) {
        Map<String, Object> response = new HashMap<>();
        try {
            // 진열수량과 재고수량도 업데이트되도록 수정
            productService.updateProduct(productDTO); // productDTO에 수정된 display_qty와 storage_qty 포함

            response.put("status", "success");
            response.put("message", "정상적으로 수정되었습니다.");
        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", "수정 실패. 다시 시도해 주세요.");
        }
        return response;
    }

}