package com.ezentwix.teamcostco.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ezentwix.teamcostco.config.BCryptUtils;
import com.ezentwix.teamcostco.dto.employee.EmployeeDTO;
import com.ezentwix.teamcostco.service.EmployeeFixService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class EmployeeFixController {

    private final EmployeeFixService employeeFixService;

    @GetMapping("/emp_fix/{emp_id}")
    public String empFix(@PathVariable Integer emp_id, Model model) {
        employeeFixService.configureModel(model);

        model.addAttribute("getOne", employeeFixService.getOne(emp_id));

        return "index";
    }

    @PostMapping("/emp_fix")
    public String empFix(@ModelAttribute EmployeeDTO empDTO, @RequestParam Integer emp_id) {

        empDTO.setEmp_id(emp_id);
        
        // 이메일 인증 토큰 발급 및 이메일 전송은 이메일이 변경된 경우에만 수행
        employeeFixService.sendVerificationEmailIfNeeded(empDTO);

        System.out.println(empDTO);
        empDTO.setLogin_pw(BCryptUtils.hashPassword(empDTO.getLogin_pw()));

        employeeFixService.fix(empDTO);

        return "redirect:/emp_detail?emp_id=" + emp_id;
    } 
    
    @GetMapping("/newtoken")
    public String verifyEmail(@RequestParam("token") String token, Model model) {
        employeeFixService.verifyEmail(token);
        return "login";
    }
}

