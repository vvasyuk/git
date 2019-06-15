package com.ignite.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("testB")
public class TestComponentB {

    @Autowired
    public void TestComponentB() {
        System.out.println("running TestComponentB");
    }
}
