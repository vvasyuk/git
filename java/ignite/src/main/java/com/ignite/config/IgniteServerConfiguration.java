package com.ignite.config;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.Ignition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Created by Jopa on 2/16/2018.
 */
//@Configuration
//@Profile("server")
public class IgniteServerConfiguration {

    Ignite ignite;

    public IgniteServerConfiguration() {
        System.out.println("starting ignite server");
        this.ignite = Ignition.start();
    }

    @Bean
    public Ignite getIgniteServer(){
        return this.ignite;
    }
}
