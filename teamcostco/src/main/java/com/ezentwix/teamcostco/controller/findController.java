package com.ezentwix.teamcostco.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ezentwix.teamcostco.config.BCryptUtils;
import com.ezentwix.teamcostco.dto.find.FindDTO;
import com.ezentwix.teamcostco.service.EmailService;
import com.ezentwix.teamcostco.service.FindService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class findController {

    private final FindService findService;
    private final EmailService emailService;

    @GetMapping("findid")
    public String find_id() {
        return "findId";
    }

    @GetMapping("findpw")
    public String find_pw() {
        return "findPw";
    }

    @PostMapping("find/id")
    public String find_id2(@ModelAttribute FindDTO findDTO, @RequestParam String emp_name, @RequestParam  String emp_email, Model model) {

        findDTO.setEmp_name(emp_name);
        findDTO.setEmp_email(emp_email);
        boolean check = findService.find_idCount(findDTO);

        String login_id = findService.find_id(findDTO);
        if (check) {
            emailService.sendEmployeeId(emp_email, login_id);
            return "redirect:/login";
        } else {
            model.addAttribute("errorMessage", "인증에 실패했습니다. 다시 시도해 주세요.");
            return "findId";
        }
    }

    @PostMapping("find/pw")
    public String find_pw2(
            @ModelAttribute FindDTO findDTO, 
            @RequestParam String emp_name, 
            @RequestParam String login_id, 
            @RequestParam String emp_email) {
                
        String ran = (int)(Math.random() * 899999) + 100000 + "";

        findDTO.setEmp_name(emp_name);
        findDTO.setLogin_id(login_id);
        findDTO.setEmp_email(emp_email);
        findDTO.setLogin_pw(BCryptUtils.hashPassword(ran));

        findService.find_pw(findDTO);
            
        boolean check = findService.find_pwCount(findDTO);


        if (check) {
            emailService.sendEmployeePw(emp_email, ran);
            return "redirect:/login";
        } else {
            return "findPw";
        }
    }
}
