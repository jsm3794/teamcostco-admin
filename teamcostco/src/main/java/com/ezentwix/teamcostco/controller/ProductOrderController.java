package com.ezentwix.teamcostco.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ezentwix.teamcostco.dto.product.NaverProductDTO;
import com.ezentwix.teamcostco.dto.product.OrderRequestDTO;
import com.ezentwix.teamcostco.pagination.PaginationResult;
import com.ezentwix.teamcostco.service.ProductOrderService;
import com.ezentwix.teamcostco.service.ProductThumbnailService;
import com.ezentwix.teamcostco.service.ThumbnailService;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class ProductOrderController {
    private final ProductOrderService productOrderService;
    private final ProductThumbnailService productThumbnailService;
    private final ThumbnailService thumbnailService;

    @GetMapping("/productorder")
    public String search(
            @RequestParam(name = "query", required = false, defaultValue = "") String query,
            @RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
            @RequestParam(name = "size", required = false, defaultValue = "15") Integer size,
            Model model) {

        productOrderService.configureModel(model);

        PaginationResult<NaverProductDTO> result = productOrderService.getPage(query, page, size);

        model.addAttribute("items", result.getData());
        model.addAttribute("count", result.getCount());
        model.addAttribute("pageDetail", result.getPageDetails());

        return "index";
    }

    @PostMapping("/productorder")
    @ResponseBody
    public String order(
        @RequestParam(value ="image_url", required = true) String image_url,
        @ModelAttribute OrderRequestDTO orderRequestDTO) {
            if(orderRequestDTO.getRequest_status() == null || orderRequestDTO.getRequest_status().isEmpty()){
                orderRequestDTO.setRequest_status("pending");
            }
        try {
            productOrderService.processOrders(orderRequestDTO);
            thumbnailService.uploadFromUrl(image_url, orderRequestDTO.getProduct_code());
            return "Order received and processed successfully";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error processing order: " + e.getMessage();
        }
    }
}