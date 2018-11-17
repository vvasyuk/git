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
import javax.cache.event.CacheEntryListenerException;
import javax.cache.event.CacheEntryUpdatedListener;
import java.util.concurrent.CompletableFuture;

public class CounterHolder {
    static Counter counter;

    static void init(Ignite ignite){
        System.out.println("starting init");
        counter = new Counter(ignite);
        System.out.println("finish init");
    }
}
