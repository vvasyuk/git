package com.ignite.config;

import com.ignite.util.Utils;
import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.IgniteCompute;
import org.apache.ignite.cluster.ClusterGroup;
import org.apache.ignite.lang.IgniteCallable;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Random;
import java.util.stream.IntStream;

import static com.ignite.util.Utils.cacheGetWrapper;
import static com.ignite.util.Utils.elapsedTimeWrapper;


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
        Random generator = new Random();
        Double avg = IntStream.range(0, 10).map(i -> {
                    int idx = generator.nextInt(9);
                    return cacheGetWrapper(elapsedTimeWrapper, cache, idx).getNano()/1000000;
                }
        ).average().getAsDouble();
        System.out.println("average get time : " + avg + "milliseconds");

//////////////////////////////////////////////////////////////////////////////////////

        int id = 5;
        String res = ignite.compute().affinityCall("test", id, () -> {
            Instant a = Instant.now();
            String str = (String) cache.get(id);
            long upperCase = str.chars().filter((s)->Character.isUpperCase(s)).count();
            long lowerCase = str.chars().filter((s)->Character.isLowerCase(s)).count();
            Instant b = Instant.now();
            Duration timeElapsed = Duration.between(a, b);
            return new String("timeElapsed: " + timeElapsed + " - " + str + " | UpperCaseCount: " + upperCase + "| lowerCaseCount: " + lowerCase);
        });
        System.out.println(res);
    }

}