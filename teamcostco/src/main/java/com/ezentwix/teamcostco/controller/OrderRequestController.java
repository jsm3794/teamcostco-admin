package com.ezentwix.teamcostco.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ezentwix.teamcostco.dto.filter.OrderRequestFilterDTO;
import com.ezentwix.teamcostco.dto.product.OrderRequestDTO;
import com.ezentwix.teamcostco.pagination.PaginationResult;
import com.ezentwix.teamcostco.service.OrderRequestService;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class OrderRequestController {
    private final OrderRequestService orderRequestService;

    @GetMapping("/orderrequest")
    public String showOrderQuest(
            @RequestParam(value = "query", defaultValue = "") String query,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @ModelAttribute OrderRequestFilterDTO orderRequestFilterDTO,
            Model model) {
        orderRequestService.configureModel(model);

        System.out.println(query);
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> map = objectMapper.convertValue(orderRequestFilterDTO, Map.class);

        PaginationResult<OrderRequestDTO> result = orderRequestService.getPage(query, page, size, map);

        model.addAttribute("items", result.getData());
        model.addAttribute("count", result.getCount());
        model.addAttribute("pageDetail", result.getPageDetails());
        return "index";
    }

    @PostMapping("/orderrequest/add")
    public String getMethodName(@RequestParam String param) {
        return new String();
    }

}
