package com.ezentwix.teamcostco.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ezentwix.teamcostco.service.dashboard.DashBoardService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class IndexController {
    private final DashBoardService dashBoardService;

    @GetMapping("/")
    public String showIndex(Model model) {
        // DashBoard를 메인으로 둡니다.
        dashBoardService.populateDashboardData(model);
        return "index";
    }
}