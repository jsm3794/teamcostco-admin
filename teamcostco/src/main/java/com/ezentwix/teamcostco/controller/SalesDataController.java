package com.ezentwix.teamcostco.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ezentwix.teamcostco.dto.sales.SalesDataDTO;
import com.ezentwix.teamcostco.service.SalesDataService;

@RestController
public class SalesDataController {

    @Autowired
    private SalesDataService salesDataService;

    @GetMapping("/sales-data")
    public ResponseEntity<?> getSalesData(@RequestParam(defaultValue = "week") String period) {
        try {
            List<SalesDataDTO> salesData = salesDataService.getSalesData(period);
            List<Map<String, Object>> formattedData = salesData.stream()
                .map(data -> {
                    Map<String, Object> item = new HashMap<>();
                    item.put("salesDate", data.getFormattedSalesDate());
                    item.put("totalPrice", data.getTotalPrice());
                    return item;
                })
                .collect(Collectors.toList());
            return ResponseEntity.ok(formattedData);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
}
