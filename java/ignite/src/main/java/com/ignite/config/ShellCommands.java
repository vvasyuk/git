package com.ignite.config;

import com.ignite.domain.Book;
import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.binary.BinaryObject;
import org.apache.ignite.cache.query.QueryCursor;
import org.apache.ignite.cache.query.ScanQuery;
import org.apache.ignite.internal.util.typedef.F;
import org.apache.ignite.lang.IgniteBiPredicate;
import org.apache.ignite.lang.IgniteCallable;
import org.apache.ignite.lang.IgniteClosure;
import org.apache.ignite.resources.IgniteInstanceResource;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import javax.cache.Cache;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@ShellComponent
public class ShellCommands {

    Ignite ignite;
    IgniteCache<Integer, Book> cache;


    public ShellCommands(Ignite ignite, IgniteCache<Integer, Book> cache) {
        this.ignite = ignite;
        this.cache = cache;
    }

    @ShellMethod("computeQuery")
    public void compute(){
        Collection<List<Integer>> lists = ignite.compute().broadcast(new IgniteCallable<List<Integer>>() {

            @IgniteInstanceResource
            private Ignite ignite;

            @Override
            public List<Integer> call() throws Exception {
                return ignite.cache("test").query(new ScanQuery<>(new CustomPredicate3()).setLocal(true), new CustomClosure()).getAll();
            }
        });
        List<Integer> res = new ArrayList<>(F.flatCollections(lists));
        res.forEach(i-> System.out.println(i));
    }

    @ShellMethod("scanQuer")
    public void query(){
        ScanQuery<Integer, Book> scan = new ScanQuery<>(new CustomPredicate3());
        QueryCursor<Cache.Entry<Integer, Book>> cur = cache.query(scan);
        for (Cache.Entry<Integer, Book> e : cur) {
            System.out.println(e.getKey());
        }
    }

    private static class CustomPredicate3 implements IgniteBiPredicate<Integer, Book> {
        private static final long serialVersionUID = 1L;
        @Override
        public boolean apply(Integer i, Book book) {
            return i/1==2;
        }
    }

    private static class CustomClosure implements IgniteClosure<Cache.Entry<Integer, Book>, Integer> {
        private static final long serialVersionUID = 1L;
        @Override
        public Integer apply(Cache.Entry<Integer, Book> integerBookEntry) {
            return integerBookEntry.getKey();
        }
    }

    @ShellMethod("scanQuer")
    public void querybi(){
        IgniteCache<Integer, BinaryObject> cache1 = ignite.getOrCreateCache("test").withKeepBinary();
        ScanQuery<Integer, BinaryObject> scan = new ScanQuery<>(new CustomPredicateBi());
        QueryCursor<Cache.Entry<Integer, BinaryObject>> cur = cache.query(scan);
        for (Cache.Entry<Integer, BinaryObject> e : cur) {
            System.out.println(e.getKey() + (String) e.getValue().field("title"));
        }
    }

    private static class CustomPredicateBi implements IgniteBiPredicate<Integer, BinaryObject> {
        private static final long serialVersionUID = 1L;
        @Override
        public boolean apply(Integer i, BinaryObject b) {
            return i/1==2;
        }
    }
}
