package com.ezentwix.teamcostco.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ezentwix.teamcostco.dto.product.ProductDTO;
import com.ezentwix.teamcostco.service.DetailService;
import com.ezentwix.teamcostco.service.ProductService;

import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
public class DetailController {

    private final DetailService detailService;
    private final ProductService productService;

    @GetMapping("/detail/{productId}")
    public String getProductDetails(@PathVariable Integer productId, Model model) {
        ProductDTO product = productService.getProductById(productId);
        detailService.configureModel(model);
        model.addAttribute("product", product);
        model.addAttribute("pageTitle", "상품 상세정보"); // 페이지 제목 설정
        // 필요에 따라 다른 데이터도 추가 가능
        return "index";
    }
}
