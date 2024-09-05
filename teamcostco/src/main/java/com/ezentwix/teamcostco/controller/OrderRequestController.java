package com.ezentwix.teamcostco.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ezentwix.teamcostco.dto.filter.OrderRequestFilterDTO;
import com.ezentwix.teamcostco.dto.product.OrderRequestDTO;
import com.ezentwix.teamcostco.pagination.PaginationResult;
import com.ezentwix.teamcostco.service.OrderRequestDetailService;
import com.ezentwix.teamcostco.service.OrderRequestService;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class OrderRequestController {
    private final OrderRequestService orderRequestService;
    private final OrderRequestDetailService orderRequestDetailService;

    @GetMapping("/orderrequest")
    public String showOrderQuest(
            @RequestParam(value = "query", defaultValue = "") String query,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "15") int size,
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

    @GetMapping("/orderrequest/{request_id}/detail")
    public String getMethodName(
            @PathVariable("request_id") Long requestId,
            Model model) {
        orderRequestDetailService.configureModel(model);
        OrderRequestDTO orderRequestDTO = orderRequestDetailService.getById(requestId);
        System.out.println(orderRequestDTO);
        model.addAttribute("item", orderRequestDTO);
        return "index";
    }

}
