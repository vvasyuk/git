package com.ignite.config;

import com.ignite.domain.Book;
import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.cache.query.QueryCursor;
import org.apache.ignite.cache.query.ScanQuery;
import org.apache.ignite.lang.IgniteBiPredicate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;

import javax.cache.Cache;


/**
 * Created by Jopa on 2/16/2018.
 */
@Configuration
@Import({IgniteClientConfiguration.class})
@Profile("client")
public class ClientConfig {

    Ignite ignite;
    IgniteCache<Integer, Book> cache;

    public ClientConfig(Ignite ignite) {
        this.ignite = ignite;
        this.cache = ignite.getOrCreateCache("test");
        System.out.println("ClientConfig start");

        Book book1 = new Book("A", "ABC");
        Book book2 = new Book("B", "BCD");

        cache.put(1, book1);
        cache.put(2, book2);

        System.out.println(cache.get(1));
        System.out.println(cache.get(2));
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
