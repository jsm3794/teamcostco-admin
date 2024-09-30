package com.ezentwix.teamcostco.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ezentwix.teamcostco.dto.customer.CustomerDTO;
import com.ezentwix.teamcostco.dto.sales.SalesDTO;
import com.ezentwix.teamcostco.service.SalesDetailService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class SalesDetailController {

    private final SalesDetailService salesDetailService;
    
    @GetMapping("sales/detail/{sales_id}")
    public String detail(@PathVariable("sales_id") Integer sales_id, Model model) {
        salesDetailService.configureModel(model);

        SalesDTO dto = salesDetailService.get(sales_id);
        CustomerDTO dto2 = salesDetailService.getCustomerInfo(dto.getSocial_id());
        dto.setThubnails_url(salesDetailService.getURL(dto.getProduct_code()));

        dto.setFinal_price(dto.getTotal_price() + dto.getDelivery_fee());
        model.addAttribute("items", dto);
        model.addAttribute("items2", dto2);
        return "index";
    }
}
