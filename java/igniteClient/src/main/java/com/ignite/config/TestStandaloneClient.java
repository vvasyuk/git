package com.ignite.config;


import com.ignite.util.StandaloneClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Profile("standalone")
@Component
public class TestStandaloneClient {

    @Autowired
    public TestStandaloneClient(Environment e) {
        System.out.println("TestStandaloneClient");
        StandaloneClient sc = new StandaloneClient();

        System.out.println(sc.getIgnite().name());
    }
}
