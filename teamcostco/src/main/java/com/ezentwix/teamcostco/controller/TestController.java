package com.ezentwix.teamcostco.controller;

import java.util.List;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ezentwix.teamcostco.dto.TestDTO;
import com.ezentwix.teamcostco.service.TestService;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@RestController
public class TestController {
    private final TestService testService;

    @GetMapping("/test")
    public List<TestDTO> getMethodName(Model model) {
        List<TestDTO> list = testService.getAll();
        return list;
    }
    
}
