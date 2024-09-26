package com.ezentwix.teamcostco.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ezentwix.teamcostco.dto.filter.OrderRequestFilterDTO;
import com.ezentwix.teamcostco.dto.product.OrderRequestDTO;
import com.ezentwix.teamcostco.pagination.PaginationResult;
import com.ezentwix.teamcostco.service.OrderRequestDetailService;
import com.ezentwix.teamcostco.service.OrderRequestProcessService;
import com.ezentwix.teamcostco.service.OrderRequestService;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class OrderRequestController {
    private final OrderRequestService orderRequestService;
    private final OrderRequestDetailService orderRequestDetailService;
    private final OrderRequestProcessService orderRequestProcessService;

    @GetMapping("/orderrequest")
    public String showOrderQuest(
            @RequestParam(value = "query", defaultValue = "") String query,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "15") int size,
            @ModelAttribute OrderRequestFilterDTO orderRequestFilterDTO,
            Model model) {
        orderRequestService.configureModel(model);

        System.out.println(query);
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> map = objectMapper.convertValue(orderRequestFilterDTO, Map.class);

        PaginationResult<OrderRequestDTO> result = orderRequestService.getPage(query, page, size, map);

        model.addAttribute("items", result.getData());
        model.addAttribute("count", result.getCount());
        model.addAttribute("pageDetail", result.getPageDetails());
        return "index";
    }

    @GetMapping("/orderrequest/detail/{request_id}")
    public String showOrderRequestDetail(
            @PathVariable("request_id") Integer requestId,
            Model model) {
        orderRequestDetailService.configureModel(model);
        OrderRequestDTO orderRequestDTO = orderRequestDetailService.getById(requestId);
        System.out.println(orderRequestDTO);
        model.addAttribute("item", orderRequestDTO);
        return "index";
    }

    // POST 요청 처리 추가
    @PostMapping("/orderrequest/detail/process")
    public String process(
                    @RequestParam Integer received_qty,
                    @RequestParam Integer defective_qty,
                    @RequestParam String defective_reason,
                    @RequestParam Long product_code,
                    @RequestParam String product_name,
                    @RequestParam String mall_name,
                    @RequestParam Integer purchase_price
    ) {

        OrderRequestDTO dto = new OrderRequestDTO();

        dto.setReceived_qty(received_qty);
        dto.setDefective_qty(defective_qty);
        dto.setDefective_reason(defective_reason);
        dto.setProduct_code(product_code);
        dto.setProduct_name(product_name);
        dto.setMall_name(mall_name);
        dto.setPurchase_price(purchase_price);
        dto.setSelling_price(purchase_price * 1.2);

        boolean check = orderRequestDetailService.exist(product_code);

        if (check) {
            orderRequestDetailService.updateQTY(dto);
        } else {
            orderRequestDetailService.newProduct(dto);
        }

        orderRequestDetailService.complete(dto);

        if (defective_qty > 0) {
            orderRequestDetailService.defectiveProduct(dto);
        }

        return "redirect:/orderrequest";
    }
    
    

    
    

}
