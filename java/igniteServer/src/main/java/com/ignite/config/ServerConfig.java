package com.ignite.config;

import com.ignite.service.ServiceProxy;
import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteAtomicLong;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.Ignition;
import org.apache.ignite.cache.query.ContinuousQuery;
import org.apache.ignite.cache.query.ScanQuery;
import org.apache.ignite.configuration.CacheConfiguration;
import org.apache.ignite.configuration.DataStorageConfiguration;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.apache.ignite.lang.IgniteBiPredicate;
import org.apache.ignite.spi.communication.tcp.TcpCommunicationSpi;
import org.apache.ignite.spi.deployment.uri.UriDeploymentSpi;
import org.apache.ignite.spi.discovery.tcp.TcpDiscoverySpi;
import org.apache.ignite.spi.discovery.tcp.ipfinder.vm.TcpDiscoveryVmIpFinder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.cache.configuration.Factory;
import javax.cache.event.CacheEntryEvent;
import javax.cache.event.CacheEntryEventFilter;
import javax.cache.event.CacheEntryUpdatedListener;
import java.util.Arrays;

/**
 * Created by Jopa on 2/11/2018.
 */
@Configuration
public class ServerConfig {

    Ignite ignite;

    public ServerConfig(Environment e) throws Exception {
        System.out.println("Starting ignite server");
        IgniteConfiguration cfg = new IgniteConfiguration();

        TcpDiscoverySpi discoverySpi = new TcpDiscoverySpi();
        discoverySpi.setLocalPort(48500);
        discoverySpi.setLocalPortRange(20);
        TcpDiscoveryVmIpFinder ipFinder = new TcpDiscoveryVmIpFinder();
        ipFinder.setAddresses(Arrays.asList("127.0.0.1:48500..48520"));
        discoverySpi.setIpFinder(ipFinder);
        TcpCommunicationSpi commSpi = new TcpCommunicationSpi();
        commSpi.setLocalPort(48100);

        UriDeploymentSpi spi = new UriDeploymentSpi();
        spi.setUriList(Arrays.asList(System.getenv("GARFILE")));

        DataStorageConfiguration storageCfg = new DataStorageConfiguration();
        storageCfg.getDefaultDataRegionConfiguration().setMaxSize(100L * 1024 * 1024);
//        storageCfg.getDefaultDataRegionConfiguration().setPersistenceEnabled(true);
//        storageCfg.setStoragePath("F:\\ignite\\");
//        storageCfg.setWalPath("F:\\ignite\\");
//        storageCfg.setWalArchivePath("F:\\ignite\\");

        cfg.setDataStorageConfiguration(storageCfg);
        cfg.setDiscoverySpi(discoverySpi);
        cfg.setCommunicationSpi(commSpi);
        cfg.setPeerClassLoadingEnabled(true);
        cfg.setDeploymentSpi(spi);

        this.ignite = Ignition.start(cfg);

        IgniteAtomicLong atomicLong = ignite.atomicLong("atomicName", 0, true);

        ContinuousQuery<Integer, String> qry = new ContinuousQuery<>();
        qry.setInitialQuery(new ScanQuery<>(new IgniteBiPredicate<Integer, String>() {
            @Override public boolean apply(Integer key, String val) {
                return true;
            }
        }));
        qry.setLocalListener(new CacheEntryUpdatedListener<Integer, String>() {
            @Override public void onUpdated(Iterable<CacheEntryEvent<? extends Integer, ? extends String>> evts) {
                for (CacheEntryEvent<? extends Integer, ? extends String> e : evts){

                    System.out.println("Updated entry [key=" + e.getKey() + ", val=" + e.getValue() + ']');
                    System.out.println("Incremented value: " + atomicLong.incrementAndGet());
                }
            }
        });
        qry.setRemoteFilterFactory(new Factory<CacheEntryEventFilter<Integer, String>>() {
            @Override public CacheEntryEventFilter<Integer, String> create() {
                return new CacheEntryEventFilter<Integer, String>() {
                    @Override public boolean evaluate(CacheEntryEvent<? extends Integer, ? extends String> e) {
                        return true;
                    }
                };
            }
        });

        CacheConfiguration<Integer, String> c = new CacheConfiguration<>();
        c.setName("test");
        c.setBackups(2);
        IgniteCache cache = ignite.getOrCreateCache(c);
        ignite.cache("test").query(qry);

//        ignite.services(ignite.cluster().forServers()).deployNodeSingleton("ServiceProxy", new ServiceProxy());
//        ignite.compute().execute("service.GarExample", "a b c d e f");
//        ignite.services().serviceDescriptors().stream().forEach(i-> System.out.println(i.serviceClass()));
    }

    @Bean
    public Ignite getIgnite(){
        return this.ignite;
    }
}
