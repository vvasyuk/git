package com.ignite.util;

import com.ignite.domain.Book;
import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.Ignition;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.apache.ignite.spi.deployment.uri.UriDeploymentSpi;
import org.apache.ignite.spi.discovery.tcp.TcpDiscoverySpi;
import org.apache.ignite.spi.discovery.tcp.ipfinder.vm.TcpDiscoveryVmIpFinder;
import org.springframework.core.env.Environment;

import java.util.Arrays;

public class StandaloneClient {

    Ignite ignite;

    public StandaloneClient() {

        System.out.println("Starting ignite client");
        System.setProperty("IGNITE_SKIP_CONFIGURATION_CONSISTENCY_CHECK", String.valueOf(true));
        IgniteConfiguration cfg = new IgniteConfiguration();

        TcpDiscoverySpi spi = new TcpDiscoverySpi();
        TcpDiscoveryVmIpFinder ipFinder = new TcpDiscoveryVmIpFinder();
        ipFinder.setAddresses(Arrays.asList("127.0.0.1:49500..49520"));
        spi.setIpFinder(ipFinder);

        UriDeploymentSpi spiDeployment = new UriDeploymentSpi();
        spiDeployment.setUriList(Arrays.asList(System.getenv("GARFILE")));

        cfg.setDeploymentSpi(spiDeployment);
        cfg.setPeerClassLoadingEnabled(true);
        cfg.setClientMode(true);
        cfg.setDiscoverySpi(spi);

        this.ignite = Ignition.start(cfg);
    }

    public Ignite getIgnite() {
        return ignite;
    }
}
