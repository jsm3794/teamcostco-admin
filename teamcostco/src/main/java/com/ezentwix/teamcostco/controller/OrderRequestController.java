package com.ezentwix.teamcostco.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ezentwix.teamcostco.service.OrderRequestService;

import lombok.RequiredArgsConstructor;



@Controller
@RequiredArgsConstructor
public class OrderRequestController {
    private final OrderRequestService orderRequestService;

    @GetMapping("/orderrequest")
    public String showOrderQuest(Model model) {
        orderRequestService.configureModel(model);
        model.addAttribute("items", orderRequestService.getAll());
        return "index";
    }

    @PostMapping("/orderrequest/add")
    public String getMethodName(@RequestParam String param) {
        return new String();
    }
    
}
