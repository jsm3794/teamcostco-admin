package com.ezentwix.teamcostco.controller.dashboard;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Controller
public class DashBoardController {

    @GetMapping("/")
    public String showDashboard() {
        return "dashboard/dashboard";
    }
}
