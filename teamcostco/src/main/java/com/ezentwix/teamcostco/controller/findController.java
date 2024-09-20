package com.ezentwix.teamcostco.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @GetMapping("/findid")
    public String findId() {
        return "findId";
    }

    @GetMapping("/findpw")
    public String findPw() {
        return "findPw";
    }

    @PostMapping("/find/id")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> findIdPost(
            @ModelAttribute FindDTO findDTO,
            @RequestParam String emp_name,
            @RequestParam String emp_email) {

        Map<String, Object> response = new HashMap<>();
        
        findDTO.setEmp_name(emp_name);
        findDTO.setEmp_email(emp_email);
        
        boolean check = findService.find_idCount(findDTO);
        String login_id = findService.find_id(findDTO);

        if (check) {
            emailService.sendEmployeeId(emp_email, login_id);
            response.put("success", true);
            response.put("message", "아이디가 이메일로 전송되었습니다.");
        } else {
            response.put("success", false);
            response.put("message", "인증에 실패했습니다. 다시 시도해 주세요.");
        }

        return ResponseEntity.ok(response);
    }

    @PostMapping("/find/pw")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> findPwPost(
            @ModelAttribute FindDTO findDTO,
            @RequestParam String emp_name,
            @RequestParam String login_id,
            @RequestParam String emp_email) {

        Map<String, Object> response = new HashMap<>();
        
        String tempPassword = (int) (Math.random() * 899999) + 100000 + "";

        findDTO.setEmp_name(emp_name);
        findDTO.setLogin_id(login_id);
        findDTO.setEmp_email(emp_email);
        findDTO.setLogin_pw(BCryptUtils.hashPassword(tempPassword));

        boolean check = findService.find_pwCount(findDTO);

        if (check) {
            findService.find_pw(findDTO);
            emailService.sendEmployeePw(emp_email, tempPassword);
            response.put("success", true);
            response.put("message", "임시 비밀번호가 이메일로 전송되었습니다.");
        } else {
            response.put("success", false);
            response.put("message", "비밀번호 찾기에 실패했습니다. 다시 시도해 주세요.");
        }

        return ResponseEntity.ok(response);
    }
}
