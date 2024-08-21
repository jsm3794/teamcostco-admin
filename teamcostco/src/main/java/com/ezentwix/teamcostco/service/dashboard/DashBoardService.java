package com.ezentwix.teamcostco.service.dashboard;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DashBoardService {

    public void populateDashboardData(Model model) {
        model.addAttribute("uri", "dashboard/dashboard");
        model.addAttribute("pageTitle", "대시보드");

        model.addAttribute("cssFiles",
                List.of("/css/dashboard/styles.css"));
    }
}
