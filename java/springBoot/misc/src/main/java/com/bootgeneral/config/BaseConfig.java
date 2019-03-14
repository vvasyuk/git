package com.bootgeneral.config;

import oracle.jdbc.pool.OracleDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Properties;

@Configuration
public class BaseConfig {

    public BaseConfig() {
        System.out.println("Running BaseConfig");
    }


    @Bean("oracleDatasource")
    @Profile("jdbc")
    public DataSource dataSource() {
        String CACHE_NAME = "MYCACHE";
        OracleDataSource ods = null;
        try {
            ods = new OracleDataSource();
            ods.setURL("jdbc:oracle:thin:@//localhost:1521/XE");
            ods.setUser("system");
            ods.setPassword("q1w2e3r4t5");
            // caching parms
            ods.setConnectionCachingEnabled(true);
            ods.setConnectionCacheName("MYCACHE");
            Properties cacheProps = new Properties();
            cacheProps.setProperty("MinLimit", "1");
            cacheProps.setProperty("MaxLimit", "4");
            cacheProps.setProperty("InitialLimit", "1");
            cacheProps.setProperty("ConnectionWaitTimeout", "5");
            cacheProps.setProperty("ValidateConnection", "true");
            ods.setConnectionCacheProperties(cacheProps);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ods;
    }

}
