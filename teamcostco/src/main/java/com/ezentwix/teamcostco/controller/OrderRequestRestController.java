package com.ezentwix.teamcostco.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderRequestRestController {

    @PostMapping("/api/orderrequest/status/{requestId}")
    public  ResponseEntity<String> getData(
        @PathVariable("requestId") long requestId
    ) {
        return new ResponseEntity<>("성공", HttpStatus.OK);
    }
}

