package com.ignite.config;

import com.ignite.domain.Book;
import com.ignite.domain.Library;
import org.apache.ignite.DataRegionMetrics;
import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.Ignition;
import org.apache.ignite.binary.BinaryBasicIdMapper;
import org.apache.ignite.binary.BinaryBasicNameMapper;
import org.apache.ignite.cache.CachePeekMode;
import org.apache.ignite.cache.affinity.AffinityKey;
import org.apache.ignite.cache.query.QueryCursor;
import org.apache.ignite.cache.query.ScanQuery;
import org.apache.ignite.configuration.BinaryConfiguration;
import org.apache.ignite.configuration.CacheConfiguration;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.apache.ignite.lang.IgniteBiPredicate;
import org.apache.ignite.lang.IgniteCallable;
import org.apache.ignite.resources.IgniteInstanceResource;
import org.apache.ignite.spi.discovery.tcp.TcpDiscoverySpi;
import org.apache.ignite.spi.discovery.tcp.ipfinder.vm.TcpDiscoveryVmIpFinder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;

import javax.cache.Cache;
import java.util.Arrays;
import java.util.Collection;
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

//        BinaryConfiguration bCfg = new BinaryConfiguration();
//        bCfg.setNameMapper(new BinaryBasicNameMapper(false));
//        bCfg.setIdMapper(new BinaryBasicIdMapper(false));
//        bCfg.setClassNames(Arrays.asList(Integer.class.getName(), Book.class.getName()));
//        cfg.setBinaryConfiguration(bCfg);

        this.ignite = Ignition.start(cfg);

        CacheConfiguration<Integer, Book> cacheCfg = new CacheConfiguration<>("test");
        this.cache = ignite.getOrCreateCache(cacheCfg);//.withKeepBinary();
        testSimple(ignite);

    }

    public static void testSimple(Ignite ignite){
        IgniteCache<Integer, Object> bookC = ignite.getOrCreateCache("bookC");
        Book b1 = new Book("1", "auth1", "title1");
        Book b2 = new Book("1", "auth2", "title1");
        Book b3 = new Book("1", "auth3", "title1");
        Book b4 = new Book("1", "auth4", "title1");
        Book b5 = new Book("1", "auth5", "title1");

        bookC.put(1, b1);
        bookC.put(2, b2);
        bookC.put(3, b3);
        bookC.put(4, b4);
        bookC.put(5, b5);

        System.out.println("bookC size: " + bookC.size(CachePeekMode.ALL));
        checkNodes(ignite);

    }

    public static void checkNodes(Ignite ignite){
        Collection<Long> lists = ignite.compute().broadcast(new IgniteCallable<Long>() {

            @IgniteInstanceResource
            private Ignite ign;

            @Override
            public Long call() throws Exception {
                System.out.println("server name: " + ignite.cluster().localNode().id() + " cache size: " + ign.getOrCreateCache("bookC").localSize(CachePeekMode.ALL));

                return 1l;
            }
        });
        lists.forEach(i-> System.out.println(i));
    }

    public static void testAff(Ignite ignite){
        //AffinityKey bookKey1 = new AffinityKey("myBookId1", "myLibId");
        //AffinityKey bookKey2 = new AffinityKey("myBookId2", "myLibId");

        //IgniteCache<Object, Library> libC = ignite.getOrCreateCache("libC");
        //libC.put("myLibId", new Library("myLibId", "myLibrary"));

        //bookC.put("myLibId", new Library("myLibId", "myLibrary"));
        //bookC.put(bookKey1, new Book(bookKey1, "auth1", "title1"));
        //bookC.put(bookKey2, new Book(bookKey2, "auth2", "title2"));
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



