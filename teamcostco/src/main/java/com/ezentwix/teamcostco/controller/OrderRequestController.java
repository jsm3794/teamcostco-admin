package com.ezentwix.teamcostco.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @GetMapping("/orderrequest/process/{request_id}")
    public String showOrderRequestProcess(
            @PathVariable("request_id") Integer requestId,
            Model model) {
        orderRequestProcessService.configureModel(model);
        OrderRequestDTO orderRequestDTO = orderRequestProcessService.getById(requestId);
        model.addAttribute("item", orderRequestDTO);
        return "index";
    }

    // POST 요청 처리 추가 (발주 상태 변경 로직 포함)
    @PostMapping("/orderrequest/detail/{request_id}")
    public ResponseEntity<Map<String, Object>> processOrder(
            @PathVariable("request_id") Integer requestId,
            @RequestBody Map<String, Object> requestBody) {
        Map<String, Object> response = new HashMap<>();
        try {
            Integer qty = (Integer) requestBody.get("qty");
            String type = (String) requestBody.get("type");
            String status = (String) requestBody.get("status");  // 상태 필드 추가

            // 수령 수량 처리
            if ("received".equals(type)) {
                orderRequestProcessService.processReceivedQty(requestId, qty);
            } 
            // 불량 수량 처리
            else if ("defective".equals(type)) {
                orderRequestProcessService.processDefectiveQty(requestId, qty);
            } 
            // 발주 상태 처리
            else if ("status".equals(type)) {
                OrderRequestDTO orderRequestDTO = new OrderRequestDTO();
                orderRequestDTO.setRequest_id(requestId);
                orderRequestDTO.setRequest_status(status);  // 상태 설정
                boolean isUpdated = orderRequestProcessService.updateOrderStatus(orderRequestDTO);

                if (!isUpdated) {
                    response.put("success", false);
                    response.put("message", "상태 변경에 실패했습니다.");
                    return ResponseEntity.badRequest().body(response);
                }
            } 
            // 잘못된 타입일 경우
            else {
                response.put("success", false);
                response.put("message", "Invalid type");
                return ResponseEntity.badRequest().body(response);
            }

            response.put("success", true);
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            e.printStackTrace();  // 로그에 스택 트레이스를 출력
            response.put("success", false);
            response.put("message", "Error processing request");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
