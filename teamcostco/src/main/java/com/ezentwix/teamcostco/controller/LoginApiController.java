package com.ezentwix.teamcostco.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ezentwix.teamcostco.dto.employee.EmployeeDTO;
import com.ezentwix.teamcostco.service.LoginService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class LoginApiController {
    private final LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(
            @RequestParam String id,
            @RequestParam String pw,
            HttpSession session) {
                
                // 유효성 검사
        if (id == null || id.isEmpty() || pw == null || pw.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("fail", "아이디 혹은 비밀번호가 비어있습니다."));
        }

        // 로그인 시도
        EmployeeDTO emp = loginService.login(id, pw);
        System.out.println(id);
        System.out.println(pw);
        System.out.println(emp);

        String token = loginService.getToken(id);

    
        if (emp == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("fail", "아이디 혹은 비밀번호가 다릅니다."));
        }

        if (token.equals("N")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("fail", "이메일 인증이 확인되지 않았습니다."));
        }

        loginService.setSession(session, emp);

        return ResponseEntity.ok(Map.of("ok", "로그인 성공"));
    }
}
