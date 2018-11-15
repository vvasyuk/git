package service;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.cache.query.ContinuousQuery;
import org.apache.ignite.events.CacheEvent;
import org.apache.ignite.events.EventType;
import org.apache.ignite.lang.IgniteAsyncCallback;
import org.apache.ignite.lang.IgnitePredicate;

import javax.cache.configuration.Factory;
import javax.cache.event.CacheEntryEvent;
import javax.cache.event.CacheEntryEventFilter;
import javax.cache.event.CacheEntryUpdatedListener;
import java.util.concurrent.CompletableFuture;

public class CounterHolder {
    static Counter counter;

    static void init(Ignite ignite){
        counter = new Counter();
        System.out.println("starting registerCacheCreationListener");
        registerCacheCreationListener(ignite);
        System.out.println("finish registerCacheCreationListener");
    }

    private static void registerCacheCreationListener(Ignite ignite){

        IgnitePredicate<CacheEvent> locLsnr = new IgnitePredicate<CacheEvent>(){
            @Override
            public boolean apply(CacheEvent evt) {
                System.out.println("Received event [evt=" + evt.name() + " cacheName=" + evt.cacheName());

                System.out.println("starting future");

                CompletableFuture<String> fut = CompletableFuture.supplyAsync(()->{
                    System.out.println("inside future");
                    while (ignite.cache(evt.cacheName())== null){
                        System.out.println(evt.cacheName() + " is null");
                        continue;
                    }
                    System.out.println("inside future, after while");

                    startCQ(evt.cacheName(), ignite);
                    System.out.println("CQ finished");
                    return "";
                });

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("finish future");
                return true; // Continue listening.
            }
        };
        ignite.events().localListen(locLsnr, EventType.EVT_CACHE_STARTED);
    }

    static void startCQ(String cacheName, Ignite ign){
        ContinuousQuery<Integer, String> qry = new ContinuousQuery<>();
        qry.setRemoteFilterFactory(new Factory<CacheEntryEventFilter<Integer, String>>() {
            @Override public CacheEntryEventFilter<Integer, String> create() {
                return new CacheEntryEventFilter<Integer, String>() {
                    @Override public boolean evaluate(CacheEntryEvent<? extends Integer, ? extends String> e) {
                        return true;
                    }
                };
            }
        });
        qry.setLocalListener(new MyCacheEntryUpdatedListener());
        System.out.println("starting CQ for" + cacheName);
        ign.cache(cacheName).query(qry);
    }

    @IgniteAsyncCallback
    static class MyCacheEntryUpdatedListener implements CacheEntryUpdatedListener<Integer, String> {
        @Override public void onUpdated(Iterable<CacheEntryEvent<? extends Integer, ? extends String>> evts) {
            for (CacheEntryEvent<? extends Integer, ? extends String> e : evts){
                System.out.println("Updated entry [key=" + e.getKey() + ", val=" + e.getValue() + ']');
                CounterHolder.counter.incrementI();
            }
        }
    }
}
