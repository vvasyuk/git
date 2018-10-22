package com.ignite.config;

import com.ignite.domain.Book;
import org.apache.ignite.*;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.apache.ignite.spi.deployment.uri.UriDeploymentSpi;
import org.apache.ignite.spi.discovery.tcp.TcpDiscoverySpi;
import org.apache.ignite.spi.discovery.tcp.ipfinder.vm.TcpDiscoveryVmIpFinder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;


import java.util.Arrays;


/**
 * Created by Jopa on 2/16/2018.
 */
@Configuration
public class ClientConfig {

    Ignite ignite;
    IgniteCache<Integer, Book> cache;

    public ClientConfig(Environment e) {

        System.out.println("Starting ignite client");
        System.setProperty("IGNITE_SKIP_CONFIGURATION_CONSISTENCY_CHECK", String.valueOf(true));
        IgniteConfiguration cfg = new IgniteConfiguration();

        TcpDiscoverySpi spi = new TcpDiscoverySpi();
        TcpDiscoveryVmIpFinder ipFinder = new TcpDiscoveryVmIpFinder();
        ipFinder.setAddresses(Arrays.asList("127.0.0.1:48500..48520"));
        spi.setIpFinder(ipFinder);

//        UriDeploymentSpi spiDeployment = new UriDeploymentSpi();
//        spiDeployment.setUriList(Arrays.asList(System.getenv("GARFILE")));
//
//        cfg.setDeploymentSpi(spiDeployment);
        cfg.setPeerClassLoadingEnabled(true);
        cfg.setClientMode(true);
        cfg.setDiscoverySpi(spi);

        this.ignite = Ignition.start(cfg);
        //testStream(ignite);

    }

    @Bean
    public Ignite getIgnite(){
        return this.ignite;
    }

    @Bean
    public IgniteCache<Integer, Book> getCache(){
        return this.cache;
    }
//    public void generateStrings(){
//
//        Random generator = new Random();
//        Double avg = IntStream.range(0, 10).map(i -> {
//                    int idx = generator.nextInt(9);
//                    return cacheGetWrapper(elapsedTimeWrapper, cache, idx).getNano()/1000000;
//                }
//        ).average().getAsDouble();
//        System.out.println("average get time : " + avg + "milliseconds");
//    }
//
//    public void affinityCall(){
//        int id = 5;
//        String res = ignite.compute().affinityCall("test", id, () -> {
//            Instant a = Instant.now();
//            Book str = (Book) cache.get(id);
////            long upperCase = str.chars().filter((s)->Character.isUpperCase(s)).count();
////            long lowerCase = str.chars().filter((s)->Character.isLowerCase(s)).count();
//            Instant b = Instant.now();
//            Duration timeElapsed = Duration.between(a, b);
//            return new String("timeElapsed: " + timeElapsed + " - " + str + " | UpperCaseCount: " + "| lowerCaseCount: ");
//        });
//        System.out.println(res);
//    }

    }



