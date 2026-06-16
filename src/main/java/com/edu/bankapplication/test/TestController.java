package com.edu.bankapplication;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/test")
    public void test(HttpServletResponse response) throws Exception {
        response.getWriter().write("HELLO");
        response.flushBuffer();
    }
}