package com.edu.bankapplication.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FailController {

    @GetMapping("/fail")
    public String fail() {
        throw new RuntimeException("FAIL");
    }
}