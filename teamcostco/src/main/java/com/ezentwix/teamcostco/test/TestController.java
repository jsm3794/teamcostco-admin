package com.ezentwix.teamcostco.test;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@RequiredArgsConstructor
public class TestController {
    
    private final TestService testService;

    @GetMapping("/test")
    public String getMethodName(Model model,
                                @RequestParam(defaultValue = "1") int page, 
                                @RequestParam(defaultValue = "5") int size) {
       testService.configurePagingModel(model, page, size);
    
       return "test";
    }
    
}
