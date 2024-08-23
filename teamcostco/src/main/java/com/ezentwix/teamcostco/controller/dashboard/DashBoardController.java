package com.ezentwix.teamcostco.controller.dashboard;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ezentwix.teamcostco.service.dashboard.DashBoardService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class DashBoardController {
    private final DashBoardService dashBoardService;

    @GetMapping("/dashboard")
    public String showDashboard(Model model) {
        // dashboard 페이지에서 사용할 데이터를 추가합니다
        dashBoardService.configureDashboardData(model);
        return "index";
    }

    
}
