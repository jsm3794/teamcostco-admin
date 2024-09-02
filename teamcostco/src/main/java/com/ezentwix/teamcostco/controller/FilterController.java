package com.ezentwix.teamcostco.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ezentwix.teamcostco.dto.filter.FilterDTO;
import com.ezentwix.teamcostco.service.FilterService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class FilterController {
    private final FilterService filterService;

    @GetMapping("/api/filter")
    public List<FilterDTO> getMethodName(@RequestParam(value ="filtertype") String filterType) {
        System.out.println(filterType);
        if(filterType == null || filterType.isEmpty()){
            return List.of();
        }

        List<FilterDTO> result = filterService.getFilter(filterType);

        return result;
    }

}