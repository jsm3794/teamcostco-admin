package com.ezentwix.teamcostco.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ezentwix.teamcostco.dto.product.NaverProductDTO;
import com.ezentwix.teamcostco.pagination.PaginationResult;
import com.ezentwix.teamcostco.service.ProductOrderService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ProductOrderController {
    private final ProductOrderService productOrderService;

    @GetMapping("/productorder")
    public String search(
            @RequestParam(name = "query", required = false, defaultValue = "") String query,
            @RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
            @RequestParam(name = "size", required = false, defaultValue = "10") Integer size,
            Model model) {

        // 기존의 configureModel 호출
        productOrderService.configureModel(model);

        // search 메소드 호출
        PaginationResult<NaverProductDTO> result = null;

        result = productOrderService.getPage(query, page, size);

        model.addAttribute("items", result.getData());
        model.addAttribute("pageDetail", result.getPageDetails());

        // 결과 뷰 반환
        return "index";
    }
}
