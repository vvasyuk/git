package com.rest.config;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    @RequestMapping("/hi")
    public String greeting() {
        return "hello";
    }
}
