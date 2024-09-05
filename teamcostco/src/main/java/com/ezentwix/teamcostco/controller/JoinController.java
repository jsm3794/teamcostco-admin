package com.ezentwix.teamcostco.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
@RequiredArgsConstructor
public class JoinController {

    @GetMapping("/join")
    public String join() {
        return "join";
    }

    @PostMapping("/join")
    public String successJoin() {
        return "login";
    }
    
    
}
