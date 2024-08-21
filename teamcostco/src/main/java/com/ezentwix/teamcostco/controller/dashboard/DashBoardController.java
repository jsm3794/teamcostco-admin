package com.ezentwix.teamcostco.controller.dashboard;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.ezentwix.teamcostco.service.ProductsService;

import org.springframework.ui.Model;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Controller
public class DashBoardController {

    private final ProductsService productsService;

    @GetMapping("/")
    public String showDashboard(Model model) {

        model.addAttribute("products", productsService.list());
        return "dashboard/dashboard";
    }
}
