package com.ezentwix.teamcostco.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ezentwix.teamcostco.config.BCryptUtils;
import com.ezentwix.teamcostco.dto.join.JoinDTO;
import com.ezentwix.teamcostco.service.EmailService;
import com.ezentwix.teamcostco.service.JoinService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class JoinController {

    private final JoinService joinService;
    private final EmailService emailService;

    @GetMapping("/join")
    public String join() {
        return "join";
    }

    @PostMapping("/join")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> successJoin(@ModelAttribute JoinDTO joinDTO) {
        Map<String, Object> response = new HashMap<>();

        if (!joinDTO.getLogin_pw().equals(joinDTO.getLogin_Pw_Check())) {
            response.put("success", false);
            response.put("message", "비밀번호가 틀립니다.");
            return ResponseEntity.badRequest().body(response);
        }

        if (!joinService.countId(joinDTO.getLogin_id())) {
            response.put("success", false);
            response.put("message", "중복된 아이디입니다.");
            return ResponseEntity.badRequest().body(response);
        }

        String token = UUID.randomUUID().toString(); // 인증 토큰 생성
        joinDTO.setEmailVerificationToken(token);
        joinDTO.setLogin_pw(BCryptUtils.hashPassword(joinDTO.getLogin_pw()));

        joinService.add(joinDTO);
        emailService.sendVerificationEmail(joinDTO.getEmp_email(), token);

        response.put("success", true);
        response.put("message", "회원가입이 성공적으로 완료되었습니다. 이메일을 확인하여 인증을 완료하세요.");

        return ResponseEntity.ok(response);
    }

    @GetMapping("/verify")
    public String verifyEmail(@RequestParam("token") String token, Model model) {
        if (joinService.verifyEmail(token)) {
            model.addAttribute("message", "이메일 인증이 완료되었습니다.");
        } else {
            model.addAttribute("message", "인증 실패. 유효하지 않은 토큰입니다.");
        }
        return "login";
    }

    @GetMapping("/check-id")
    @ResponseBody
    public Map<String, Boolean> checkId(@RequestParam("id") String id) {
        boolean isAvailable = joinService.countId(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("available", isAvailable);
        return response;
    }
}
