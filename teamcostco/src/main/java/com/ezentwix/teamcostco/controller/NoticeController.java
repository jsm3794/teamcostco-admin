package com.ezentwix.teamcostco.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ezentwix.teamcostco.dto.notice.NoticeDTO;
import com.ezentwix.teamcostco.pagination.PaginationResult;
import com.ezentwix.teamcostco.service.NoticeService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class NoticeController {
    private final NoticeService noticeService;

    @GetMapping("/notice")
    public String showNotice(
            @RequestParam(value = "query", defaultValue = "") String query,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            Model model) {
        noticeService.configureModel(model);

        PaginationResult<NoticeDTO> result = noticeService.getPage(query, page, size, null);

        model.addAttribute("notices", noticeService.getAll());
        model.addAttribute("pageDetail", result.getPageDetails());

        return "index";
    }
}
