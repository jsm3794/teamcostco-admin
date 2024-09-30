package com.ezentwix.teamcostco.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ezentwix.teamcostco.dto.sales.SalesDTO;
import com.ezentwix.teamcostco.pagination.PaginationResult;
import com.ezentwix.teamcostco.service.SalesService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class SalesController {

    private final SalesService salesService;

    @GetMapping("/sales")
    public String sales(Model model,
            @RequestParam(value = "query", defaultValue = "") String query,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "15") int size) {
        salesService.configureModel(model);

        PaginationResult<SalesDTO> result = salesService.getPage(query, page, size, null);

        model.addAttribute("items", result.getData());
        model.addAttribute("count", result.getCount());
        model.addAttribute("pageDetail", result.getPageDetails());

        return "index";
    }

}