package com.ezentwix.teamcostco.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ezentwix.teamcostco.dto.product.NaverProductDTO;
import com.ezentwix.teamcostco.dto.product.NaverProductResponseDTO;
import com.ezentwix.teamcostco.service.ProductOrderService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ProductOrderController {
    private final ProductOrderService productOrderService;

    @GetMapping("/productorder")
    public String search(
            @RequestParam(name = "query", required = false, defaultValue = "") String query,
            @RequestParam(name = "display", required = false, defaultValue = "10") Integer display,
            @RequestParam(name = "start", required = false, defaultValue = "1") Integer start,
            Model model) {

        // 기존의 configureModel 호출
        productOrderService.configureModel(model);

        // search 메소드 호출
        NaverProductResponseDTO nprDTO = null;
        if (!query.isEmpty()) {
            nprDTO = productOrderService.search(query, display, start);

            List<NaverProductDTO> products = nprDTO.getItems();
            model.addAttribute("items", products);
        }

        // 결과 뷰 반환
        return "index";
    }
}
