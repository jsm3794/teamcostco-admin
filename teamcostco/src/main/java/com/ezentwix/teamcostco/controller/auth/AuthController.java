package com.ezentwix.teamcostco.controller.auth;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ezentwix.teamcostco.dto.EmployeeDTO;
import com.ezentwix.teamcostco.service.auth.AuthService;
import com.ezentwix.teamcostco.service.dashboard.DashBoardService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final DashBoardService dashBoardService;

    @GetMapping("/login")
    public String showLogin() {
        return "auth/login";
    }

    @PostMapping("/login")
    public String login(
            @RequestParam(name = "id", required = false) String id,
            @RequestParam(name = "pw", required = false) String pw,
            Model model) {

        if (id == null || pw == null || id.isEmpty() || pw.isEmpty()) {
            return "redirect:/login";
        }

        System.out.println(id + " " + pw);
        EmployeeDTO emp = authService.login(id, pw);
        if (emp == null) {
            return "auth/login";
        }

        dashBoardService.configureModel(model);
        return "redirect:/";
    }

}
