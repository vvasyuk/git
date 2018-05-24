package com.ignite.config;

import org.apache.ignite.Ignite;
import org.apache.ignite.Ignition;
import org.apache.ignite.configuration.DataStorageConfiguration;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.apache.ignite.spi.communication.tcp.TcpCommunicationSpi;
import org.apache.ignite.spi.discovery.tcp.TcpDiscoverySpi;
import org.apache.ignite.spi.discovery.tcp.ipfinder.vm.TcpDiscoveryVmIpFinder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import java.util.Arrays;

/**
 * Created by Jopa on 2/11/2018.
 */
@Configuration
public class ServerConfig {

    Ignite ignite;

    public ServerConfig(Environment e) throws Exception {
        System.out.println("Starting ignite server");
        IgniteConfiguration cfg = new IgniteConfiguration();

        TcpDiscoverySpi discoverySpi = new TcpDiscoverySpi();
        discoverySpi.setLocalPort(48500);
        discoverySpi.setLocalPortRange(20);
        TcpDiscoveryVmIpFinder ipFinder = new TcpDiscoveryVmIpFinder();
        ipFinder.setAddresses(Arrays.asList("127.0.0.1:48500..48520"));
        discoverySpi.setIpFinder(ipFinder);
        TcpCommunicationSpi commSpi = new TcpCommunicationSpi();
        commSpi.setLocalPort(48100);

        DataStorageConfiguration storageCfg = new DataStorageConfiguration();
        storageCfg.getDefaultDataRegionConfiguration().setMaxSize(100L * 1024 * 1024);
        storageCfg.getDefaultDataRegionConfiguration().setPersistenceEnabled(true);
        storageCfg.setStoragePath("c:\\Users\\vvasy\\Downloads\\ignitePersistence\\");
        storageCfg.setWalPath("c:\\Users\\vvasy\\Downloads\\ignitePersistence\\");
        storageCfg.setWalArchivePath("c:\\Users\\vvasy\\Downloads\\ignitePersistence\\");

        cfg.setDataStorageConfiguration(storageCfg);
        cfg.setDiscoverySpi(discoverySpi);
        cfg.setCommunicationSpi(commSpi);
        cfg.setPeerClassLoadingEnabled(true);

        this.ignite = Ignition.start(cfg);
    }

    @Bean
    public Ignite getIgnite(){
        return this.ignite;
    }
}
