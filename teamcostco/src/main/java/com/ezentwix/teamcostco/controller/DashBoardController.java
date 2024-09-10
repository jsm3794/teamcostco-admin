package com.ezentwix.teamcostco.controller;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ezentwix.teamcostco.dto.product.ProductSummaryDTO;
import com.ezentwix.teamcostco.dto.product.RequestAndProductDTO;
import com.ezentwix.teamcostco.dto.sales.OrderTrackDTO;
import com.ezentwix.teamcostco.service.DashBoardService;
import com.ezentwix.teamcostco.service.ProductService;
import com.ezentwix.teamcostco.service.SalesDataService;

import lombok.RequiredArgsConstructor;
@Controller
@RequiredArgsConstructor
public class DashBoardController {
    private final DashBoardService dashBoardService;
    private final ProductService productService;
    private final SalesDataService salesDataService;

    @GetMapping("/dashboard")
    public String showDashboard(Model model) {
        dashBoardService.configureModel(model);
        model.addAttribute("totalSales", salesDataService.getTotalSales());
        model.addAttribute("operatingProfit", salesDataService.getOperatingProfit()); 
        return "index";
    }

    @GetMapping("/dashboard/productsummary")
    @ResponseBody
    public ProductSummaryDTO getDashboardProductSummary() {
        return productService.eachProductCount();
    }
    @GetMapping(value="/totalproductsbyupdate", produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Integer getTotalProductsByUpdate() {
        return productService.getTotalProductsByUpdateDate();
    }
    @GetMapping("/requestqty")
    @ResponseBody
    public List<RequestAndProductDTO> getRequestAndProductInfo() {
        return dashBoardService.getRequestAndProductInfo();
    }

    @GetMapping("/api/track")
    @ResponseBody
    public OrderTrackDTO getOrderTrack() {
        return dashBoardService.getOrderTrackDTO();
    }
}