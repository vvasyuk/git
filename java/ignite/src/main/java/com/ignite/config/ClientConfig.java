package com.ignite.config;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;

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
        System.out.println(cache.get("1"));
    }

}
