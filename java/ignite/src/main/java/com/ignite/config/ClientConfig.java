package com.ignite.config;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;

import java.time.Duration;
import java.time.Instant;
import java.util.stream.IntStream;

/**
 * Created by Jopa on 2/16/2018.
 */
@Configuration
@Import({IgniteClientConfiguration.class})
@Profile("client")
public class ClientConfig {

    public ClientConfig(Ignite ignite) {
        System.out.println("ClientConfig start");
        IgniteCache<Object, Object> cache = ignite.getOrCreateCache("test");
        IntStream.range(0, 10).forEach(i -> {
            Instant a = Instant.now();
            String s = (String) cache.get(i);
            Instant b = Instant.now();
            Duration timeElapsed = Duration.between(a, b);
            System.out.println(s + " | elapsed time : " + timeElapsed);
        });
    }

}
