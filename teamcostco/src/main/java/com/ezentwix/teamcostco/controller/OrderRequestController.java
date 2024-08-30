package com.ezentwix.teamcostco.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ezentwix.teamcostco.dto.product.OrderRequestDTO;
import com.ezentwix.teamcostco.pagination.PaginationResult;
import com.ezentwix.teamcostco.service.OrderRequestService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class OrderRequestController {
    private final OrderRequestService orderRequestService;

    @GetMapping("/orderrequest")
    public String showOrderQuest(
            @RequestParam(defaultValue = "") String query,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            Model model) {
        orderRequestService.configureModel(model);
        model.addAttribute("items", orderRequestService.getAll());
        PaginationResult<OrderRequestDTO> result = orderRequestService.getPage(query, 0, 0, Map.of());

        model.addAttribute("pageDetail", result.getPageDetails());
        return "index";
    }

    @PostMapping("/orderrequest/add")
    public String getMethodName(@RequestParam String param) {
        return new String();
    }

}
