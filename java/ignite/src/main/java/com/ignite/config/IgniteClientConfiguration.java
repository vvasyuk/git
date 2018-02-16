package com.ignite.config;

import org.apache.ignite.Ignite;
import org.apache.ignite.Ignition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Created by Jopa on 2/16/2018.
 */
public class IgniteClientConfiguration {
    Ignite ignite;

    public IgniteClientConfiguration() {
        System.out.println("starting ignite client");
        Ignition.setClientMode(true);
        this.ignite = Ignition.start();
    }

    @Bean
    public Ignite getIgniteClient(){
        return this.ignite;
    }
}
