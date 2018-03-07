package com.ignite.config;

import com.ignite.domain.Book;
import com.ignite.util.Utils;
import org.apache.ignite.DataRegionMetrics;
import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.Ignition;
import org.apache.ignite.configuration.DataStorageConfiguration;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.apache.ignite.spi.discovery.tcp.TcpDiscoverySpi;
import org.apache.ignite.spi.discovery.tcp.ipfinder.vm.TcpDiscoveryVmIpFinder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

import static com.ignite.util.Utils.createDataSize;

/**
 * Created by Jopa on 2/11/2018.
 */
@Configuration
@Profile("server")
public class ServerConfig {

    Ignite ignite;
    IgniteCache<Integer, Book> cache;

    public ServerConfig(Environment e) throws Exception {
        System.out.println("Starting ignite server");
        IgniteConfiguration cfg = new IgniteConfiguration();

        TcpDiscoverySpi spi = new TcpDiscoverySpi();
        TcpDiscoveryVmIpFinder ipFinder = new TcpDiscoveryVmIpFinder();
        ipFinder.setAddresses(Arrays.asList(e.getProperty("servers", "localhost").split(",")));
        spi.setIpFinder(ipFinder);

        DataStorageConfiguration storageCfg = new DataStorageConfiguration();
        storageCfg.getDefaultDataRegionConfiguration().setMaxSize(1L * 1024 * 1024 * 1024);
        storageCfg.setMetricsEnabled(true);

        Map<String, String> map = new HashMap<>();
        map.put("KEY", "VALUE");

        cfg.setDataStorageConfiguration(storageCfg);
        cfg.setDiscoverySpi(spi);
        cfg.setUserAttributes(map);

        cfg.setPeerClassLoadingEnabled(true);

        this.ignite = Ignition.start(cfg);
        this.cache = ignite.getOrCreateCache("test");
    }

    @Bean
    public Ignite getIgnite(){
        return this.ignite;
    }

    @Bean
    public IgniteCache<Integer, Book> getCache(){
        return this.cache;
    }
}
