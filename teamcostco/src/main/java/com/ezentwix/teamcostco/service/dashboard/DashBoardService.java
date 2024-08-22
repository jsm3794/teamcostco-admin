package com.ezentwix.teamcostco.service.dashboard;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.ezentwix.teamcostco.service.products.ProductService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DashBoardService {
    private final ProductService productsService;

    public void configureDashboardData(Model model) {
        model.addAttribute("uri", "dashboard/dashboard");
        model.addAttribute("pageTitle", "대시보드");

        model.addAttribute("cssFiles",
                List.of("/css/dashboard/styles.css"));

        model.addAttribute("products", productsService.list());

        // model.addAttribute("jsFiles", null);
    }

}
