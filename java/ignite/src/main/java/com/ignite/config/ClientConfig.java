package com.ignite.config;

import com.ignite.dao.API;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Created by Jopa on 2/16/2018.
 */
@Configuration
@Profile("client")
public class ClientConfig {
    public ClientConfig() throws Exception {

        System.out.println("printing cache");
        API.printCache();
    }
}
