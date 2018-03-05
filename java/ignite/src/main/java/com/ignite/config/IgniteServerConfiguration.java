package com.ignite.config;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.Ignition;
import org.apache.ignite.configuration.DataRegionConfiguration;
import org.apache.ignite.configuration.DataStorageConfiguration;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Created by Jopa on 2/16/2018.
 */

public class IgniteServerConfiguration {

    Ignite ignite;

    public IgniteServerConfiguration() {
        System.out.println("starting ignite server");
        IgniteConfiguration cfg = new IgniteConfiguration();
        DataStorageConfiguration storageCfg = new DataStorageConfiguration();

        storageCfg.getDefaultDataRegionConfiguration().setMaxSize(1L * 1024 * 1024 * 1024);
        storageCfg.setMetricsEnabled(true);
        cfg.setDataStorageConfiguration(storageCfg);
        cfg.setPeerClassLoadingEnabled(true);

        this.ignite = Ignition.start(cfg);
    }

    @Bean
    public Ignite getIgniteServer(){
        return this.ignite;
    }
}
