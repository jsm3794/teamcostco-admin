package com.ezentwix.teamcostco.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import com.ezentwix.teamcostco.dto.filter.ProductDefectiveFilterDTO;
import com.ezentwix.teamcostco.dto.product.ProductDefectiveDTO;
import com.ezentwix.teamcostco.pagination.PaginationResult;
import com.ezentwix.teamcostco.service.ProductDefectiveService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ProductDefectiveController {
    private final ProductDefectiveService productDefectiveService;

    @GetMapping("/productdefective")
    public String showDefective(Model model,
            @RequestParam(name = "query", required = false, defaultValue = "") String query,
            @RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
            @RequestParam(name = "size", required = false, defaultValue = "15") Integer size,
            @ModelAttribute ProductDefectiveFilterDTO productDefectiveFilterDTO) {

        productDefectiveService.configureModel(model);

        System.out.println(query + "test~~~~~~~~~~~~~~~~~~");
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> map = objectMapper.convertValue(productDefectiveFilterDTO, Map.class);

        PaginationResult<ProductDefectiveDTO> result = productDefectiveService.getPage(query, page, size, map);

        model.addAttribute("items", result.getData());
        model.addAttribute("count", result.getCount());
        model.addAttribute("pageDetail", result.getPageDetails());
        model.addAttribute("defectiveproducts", productDefectiveService.getAll());
        System.out.println("tes@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@t");
        return "index";
    }


}
