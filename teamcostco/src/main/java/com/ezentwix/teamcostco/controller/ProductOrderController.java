package com.ezentwix.teamcostco.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ezentwix.teamcostco.dto.product.NaverProductDTO;
import com.ezentwix.teamcostco.dto.product.OrderRequestDTO;
import com.ezentwix.teamcostco.pagination.PaginationResult;
import com.ezentwix.teamcostco.service.ProductOrderService;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class ProductOrderController {
    private final ProductOrderService productOrderService;

    @GetMapping("/productorder")
    public String search(
            @RequestParam(name = "query", required = false, defaultValue = "") String query,
            @RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
            @RequestParam(name = "size", required = false, defaultValue = "15") Integer size,
            Model model) {

        productOrderService.configureModel(model);

        PaginationResult<NaverProductDTO> result = productOrderService.getPage(query, page, size);

        model.addAttribute("items", result.getData());
        model.addAttribute("count", result.getCount());
        model.addAttribute("pageDetail", result.getPageDetails());

        return "index";
    }

    @PostMapping("/productorder")
    @ResponseBody
    public String order(
        @ModelAttribute OrderRequestDTO orderRequestDTO) {
            System.out.println(orderRequestDTO);
        try {
            productOrderService.processOrders(orderRequestDTO);
            return "Order received and processed successfully";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error processing order: " + e.getMessage();
        }
    }
}