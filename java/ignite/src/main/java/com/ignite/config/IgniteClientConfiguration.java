package com.ignite.config;

import com.ignite.domain.Book;
import org.apache.ignite.Ignite;
import org.apache.ignite.Ignition;
import org.apache.ignite.binary.BinaryBasicIdMapper;
import org.apache.ignite.binary.BinaryBasicNameMapper;
import org.apache.ignite.configuration.BinaryConfiguration;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.apache.ignite.spi.discovery.tcp.TcpDiscoverySpi;
import org.apache.ignite.spi.discovery.tcp.ipfinder.vm.TcpDiscoveryVmIpFinder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;

/**
 * Created by Jopa on 2/16/2018.
 */
public class IgniteClientConfiguration {
    Ignite ignite;

    public IgniteClientConfiguration() {
        System.out.println("starting ignite client");

        TcpDiscoverySpi spi = new TcpDiscoverySpi();
        TcpDiscoveryVmIpFinder ipFinder = new TcpDiscoveryVmIpFinder();
        ipFinder.setAddresses(Arrays.asList("127.0.0.1", "127.0.0.1:47500..47509"));
        spi.setIpFinder(ipFinder);


        BinaryConfiguration bCfg = new BinaryConfiguration();
        bCfg.setNameMapper(new BinaryBasicNameMapper(false));
        bCfg.setIdMapper(new BinaryBasicIdMapper(false));

        bCfg.setClassNames(Arrays.asList(Integer.class.getName(), Book.class.getName()));

        IgniteConfiguration cfg = new IgniteConfiguration();
        //cfg.setBinaryConfiguration(bCfg);
        cfg.setPeerClassLoadingEnabled(true);
        cfg.setClientMode(true);
        cfg.setDiscoverySpi(spi);


        this.ignite = Ignition.start(cfg);

    }

    @Bean
    public Ignite getIgniteClient(){
        return this.ignite;
    }
}
