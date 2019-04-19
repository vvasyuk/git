package com.rest.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class BaseConfig {

    public BaseConfig() {
        System.out.println("Running BaseConfig");
    }
}
