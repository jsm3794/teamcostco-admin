package com.ezentwix.teamcostco.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ezentwix.teamcostco.service.NoticeService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class NoticeController {
    private final NoticeService noticeService;
    
    @GetMapping("/notice")
    public String showNotice(Model model) {
        noticeService.configureModel(model);
        model.addAttribute("notices", noticeService.getAll());

        return "index";
    }

}
