package com.ezentwix.teamcostco.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class ProductThumbnailTestPage {
    

    @GetMapping("/thumbnail/test")
    public String test() {
        return "test";
    }
    
}
