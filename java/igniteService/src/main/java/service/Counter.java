package service;

import org.apache.ignite.Ignite;
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

public class Counter {
    private int i;
    private Ignite ignite;

    public int getI() {
        return i;
    }

    public void incrementI() {
        this.i++;
    }

    public Counter(Ignite ignite) {
        this.ignite=ignite;

        System.out.println("inside Counter, starting registerCacheCreationListener");
        registerCacheCreationListener();
        System.out.println("inside Counter, finish registerCacheCreationListener");
    }

    private void registerCacheCreationListener(){

        IgnitePredicate<CacheEvent> locLsnr = new IgnitePredicate<CacheEvent>(){
            @Override
            public boolean apply(CacheEvent evt) {
                System.out.println("Received event [evt=" + evt.name() + " cacheName=" + evt.cacheName());

                startCQ(evt.cacheName());

                System.out.println("finished listening");
                return true; // Continue listening.
            }
        };
        ignite.events().localListen(locLsnr, EventType.EVT_CACHE_STARTED);
    }

    private void startCQ(String cacheName){
        ContinuousQuery<Integer, String> qry = new ContinuousQuery<>();
        qry.setLocalListener(new MyCacheEntryUpdatedListener());

        qry.setRemoteFilter(e -> true);

        CompletableFuture<String> fut = CompletableFuture.supplyAsync(()->{

            System.out.println("before while");

            while (ignite.cache(cacheName)== null){
                System.out.println(cacheName + " is null");
                continue;
            }

            System.out.println("starting query");
            ignite.cache(cacheName).query(qry);
            System.out.println("finish query");
            return "";
        });
    }

    @IgniteAsyncCallback
    class MyCacheEntryUpdatedListener implements CacheEntryUpdatedListener<Integer, String> {
        @Override public void onUpdated(Iterable<CacheEntryEvent<? extends Integer, ? extends String>> evts) {
            for (CacheEntryEvent<? extends Integer, ? extends String> e : evts){
                System.out.println("Updated entry [key=" + e.getKey() + ", val=" + e.getValue() + ']');
            }
        }
    }
}
