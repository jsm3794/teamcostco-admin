package com.ezentwix.teamcostco.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ezentwix.teamcostco.dto.employee.EmployeeDTO;
import com.ezentwix.teamcostco.pagination.PaginationResult;
import com.ezentwix.teamcostco.service.EmployeeDetailService;
import com.ezentwix.teamcostco.service.EmployeeService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;
    private final EmployeeDetailService employeeDetailService;

    @GetMapping("/emp_list")
    public String showEmployee(Model model,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {

        employeeService.configureModel(model);

        // model.addAttribute("empList", employeeService.getEmpList());
        PaginationResult<EmployeeDTO> result = employeeService.getPage(page, size);
        model.addAttribute("empList", result.getData());
        model.addAttribute("pageDetail", result.getPageDetails());
        return "index";
    }

    @GetMapping("/emp_detail")
    public String showEmployeeDetail(
            @RequestParam(name = "emp_id", required = false) Integer emp_id,
            HttpSession session, Model model) {
        if (emp_id == null) {
            emp_id = 0;
        }
        employeeDetailService.configureModel(model);
        model.addAttribute("empDetail", employeeDetailService.getEmp(emp_id));
        return "index";
    }

    @PostMapping("/update")
    @ResponseBody
    public Map<String, Object> updateEmployee(@ModelAttribute("employee") EmployeeDTO EmployeeDTO) {
        Map<String, Object> response = new HashMap<>();
        try {
            employeeDetailService.updateEmployee(EmployeeDTO);
            response.put("status", "success");
            response.put("message", "정상적으로 수정되었습니다.");
        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", "수정 실패. 다시 시도해 주세요.");
        }
        return response;
    }
}
