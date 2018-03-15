package com.ignite.config;

import com.ignite.domain.Book;
import com.ignite.domain.Library;
import org.apache.ignite.*;
import org.apache.ignite.binary.BinaryBasicIdMapper;
import org.apache.ignite.binary.BinaryBasicNameMapper;
import org.apache.ignite.cache.CachePeekMode;
import org.apache.ignite.cache.affinity.AffinityKey;
import org.apache.ignite.cache.query.QueryCursor;
import org.apache.ignite.cache.query.ScanQuery;
import org.apache.ignite.configuration.BinaryConfiguration;
import org.apache.ignite.configuration.CacheConfiguration;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.apache.ignite.lang.IgniteBiInClosure;
import org.apache.ignite.lang.IgniteBiPredicate;
import org.apache.ignite.lang.IgniteCallable;
import org.apache.ignite.resources.IgniteInstanceResource;
import org.apache.ignite.spi.discovery.tcp.TcpDiscoverySpi;
import org.apache.ignite.spi.discovery.tcp.ipfinder.vm.TcpDiscoveryVmIpFinder;
import org.apache.ignite.stream.StreamVisitor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.shell.standard.ShellMethod;

import javax.cache.Cache;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;


/**
 * Created by Jopa on 2/16/2018.
 */
@Configuration
public class ClientConfig {

    Ignite ignite;
    IgniteCache<Integer, Book> cache;

    public ClientConfig(Environment e) {

        System.out.println("Starting ignite client");

        IgniteConfiguration cfg = new IgniteConfiguration();

        TcpDiscoverySpi spi = new TcpDiscoverySpi();
        TcpDiscoveryVmIpFinder ipFinder = new TcpDiscoveryVmIpFinder();
        ipFinder.setAddresses(Arrays.asList(e.getProperty("servers", "localhost").split(",")));
        spi.setIpFinder(ipFinder);

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



