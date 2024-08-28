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
import org.springframework.web.bind.annotation.ResponseBody;

import com.ezentwix.teamcostco.dto.product.ProductDTO;
import com.ezentwix.teamcostco.service.DetailService;
import com.ezentwix.teamcostco.service.ProductService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/detail")
public class DetailController {

    private final DetailService detailService;
    private final ProductService productService;

    @GetMapping("/{productId}")
    public String getProductDetails(@PathVariable Integer productId, Model model) {
        ProductDTO product = productService.getProductById(productId);
        detailService.configureModel(model);
        model.addAttribute("product", product);
        model.addAttribute("pageTitle", "상품 상세정보");
        return "index";
    }

    @PostMapping("/update")
    @ResponseBody
    public Map<String, Object> updateProduct(@ModelAttribute("product") ProductDTO productDTO) {
        Map<String, Object> response = new HashMap<>();
        try {
            productService.updateProduct(productDTO);
            response.put("status", "success");
            response.put("message", "정상적으로 수정되었습니다.");
        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", "수정 실패. 다시 시도해 주세요.");
        }
        return response;
    }
}
