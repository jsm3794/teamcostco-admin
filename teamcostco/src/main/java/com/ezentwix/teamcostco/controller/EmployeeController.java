package com.ezentwix.teamcostco.controller;

import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ezentwix.teamcostco.service.EmployeeService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("/employee")
    public String showEmployee(Model model, @RequestParam(defaultValue = "0") int page) {
        employeeService.configureModel(model);
        model.addAttribute("empList", employeeService.getEmpList(page));
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", employeeService.getTotalPages());

        return "index";
    }

     // 직책 드롭다운 데이터 가져오기
    @GetMapping("/api/job-titles")
    public ResponseEntity<Set<String>> getJobTitles() {
        Set<String> jobTitles = employeeService.getAllJobTitles();
        return ResponseEntity.ok(jobTitles);
    }

     
}
