package com.ezentwix.teamcostco.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ezentwix.teamcostco.service.DashBoardService;
import com.ezentwix.teamcostco.service.InventoryService;
import com.ezentwix.teamcostco.service.ProductOrderService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class IndexController {
    private final DashBoardService dashBoardService;
    private final InventoryService inventoryService;
    private final ProductOrderService productOrderService;

    @GetMapping("/")
    public String showIndex(Model model) {
        // DashBoard를 메인으로 둡니다.
        dashBoardService.configureModel(model);
        return "index";
    }

        @GetMapping("/{page}")
    public String handleMainPages(@PathVariable String page, Model model) {
        switch (page) {
            case "dashboard":
                dashBoardService.configureModel(model);
                break;
            case "inventory":
                inventoryService.configureModel(model);
                break;
            case "productorder":
                productOrderService.configureModel(model);
                break;

                
            default:
                return "redirect:/";
        }
        return "index";
    }
}