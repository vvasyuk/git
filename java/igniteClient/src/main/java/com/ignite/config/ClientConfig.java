package com.ignite.config;

import com.ignite.domain.Book;
import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.Ignition;
import org.apache.ignite.binary.BinaryBasicIdMapper;
import org.apache.ignite.binary.BinaryBasicNameMapper;
import org.apache.ignite.cache.query.QueryCursor;
import org.apache.ignite.cache.query.ScanQuery;
import org.apache.ignite.configuration.BinaryConfiguration;
import org.apache.ignite.configuration.CacheConfiguration;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.apache.ignite.lang.IgniteBiPredicate;
import org.apache.ignite.spi.discovery.tcp.TcpDiscoverySpi;
import org.apache.ignite.spi.discovery.tcp.ipfinder.vm.TcpDiscoveryVmIpFinder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;

import javax.cache.Cache;
import java.util.Arrays;


/**
 * Created by Jopa on 2/16/2018.
 */
@Configuration
@Profile("client")
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

//        BinaryConfiguration bCfg = new BinaryConfiguration();
//        bCfg.setNameMapper(new BinaryBasicNameMapper(false));
//        bCfg.setIdMapper(new BinaryBasicIdMapper(false));
//        bCfg.setClassNames(Arrays.asList(Integer.class.getName(), Book.class.getName()));
//        cfg.setBinaryConfiguration(bCfg);

        this.ignite = Ignition.start(cfg);

        CacheConfiguration<Integer, Book> cacheCfg = new CacheConfiguration<>("test");
        this.cache = ignite.getOrCreateCache(cacheCfg);//.withKeepBinary();
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
