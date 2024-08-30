package com.ezentwix.teamcostco.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ezentwix.teamcostco.dto.notice.BoardFixDTO;
import com.ezentwix.teamcostco.service.BoardFixService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class BoardFixController {

    private final BoardFixService boardFixService;

    @GetMapping("/fix/{notice_id}")
    public String showBoard(@PathVariable Integer notice_id, Model model) {
        boardFixService.configureModel(model);
        model.addAttribute("boardfix", boardFixService.get(notice_id));
        return "index";
    }

    @PostMapping("/fix")
    public String fixBoard(@RequestParam String title,
            @RequestParam String content,
            @RequestParam Integer notice_id) {
                
        BoardFixDTO boardFixDTO = new BoardFixDTO();
        boardFixDTO.setTitle(title);
        boardFixDTO.setContent(content);
        boardFixDTO.setNotice_id(notice_id);

        boardFixService.fix(boardFixDTO);
        return "redirect:/board?notice_id=" + notice_id;
    }
}
