package com.ezentwix.teamcostco.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ezentwix.teamcostco.service.ProductDefectiveService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ProductDefectiveController {
    private final ProductDefectiveService productDefectiveService;

    @GetMapping("/productdefective")
    public String showDefective(Model model) {

        productDefectiveService.configureModel(model);
        model.addAttribute("defectiveproducts", productDefectiveService.getAll());
        return "index";
    }


}
