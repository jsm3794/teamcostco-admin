package com.ezentwix.teamcostco.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ezentwix.teamcostco.config.BCryptUtils;
import com.ezentwix.teamcostco.dto.employee.EmployeeDTO;
import com.ezentwix.teamcostco.dto.filter.EmployeeFilterDTO;
import com.ezentwix.teamcostco.pagination.PaginationResult;
import com.ezentwix.teamcostco.service.EmployeeDetailService;
import com.ezentwix.teamcostco.service.EmployeeFixService;
import com.ezentwix.teamcostco.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;
    private final EmployeeDetailService employeeDetailService;
    private final EmployeeFixService employeeFixService;
    private final ObjectMapper objectMapper;

    @GetMapping("/emp_list")
    public String showEmployee(Model model,
            @RequestParam(defaultValue = "") String query,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "15") int size,
            @ModelAttribute EmployeeFilterDTO empFilterDTO) {

        employeeService.configureModel(model);

        System.out.println(empFilterDTO);
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> map = objectMapper.convertValue(empFilterDTO, Map.class);
        PaginationResult<EmployeeDTO> result = employeeService.getPage(query, page, size, map);
        
        model.addAttribute("empList", result.getData());
        model.addAttribute("pageDetail", result.getPageDetails());
        return "index";
    }

    @GetMapping("/emp_detail")
    public String getMethodName(
            @RequestParam(name = "emp_id", required = false) Integer emp_id,
            HttpSession session, Model model) {
        if (emp_id == null) {
            emp_id = 0;
        }
        employeeDetailService.configureModel(model);
        model.addAttribute("empDetail", employeeDetailService.getOne(emp_id));
        return "index";
    }
}
