package com.ezentwix.teamcostco.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ezentwix.teamcostco.service.WritingService;
import com.ezentwix.teamcostco.dto.notice.WritingDTO;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class WritingController {

    private final WritingService writingService;

    @GetMapping("/writing")
    public String showWriting(Model model) {
        writingService.configureModel(model);
        return "index";
    }

    @PostMapping("/writing")
    public String addWriting(@RequestParam String title, 
                             @RequestParam String content,
                             @RequestParam Integer emp_id) {
        WritingDTO writingDTO = new WritingDTO();
        writingDTO.setTitle(title);
        writingDTO.setContent(content);
        writingDTO.setEmp_id(emp_id); 

        writingService.add(writingDTO);
        return "redirect:/notice"; 
    }
}
