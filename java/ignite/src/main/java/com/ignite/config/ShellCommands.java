package com.ignite.config;

import com.ignite.domain.Book;
import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.cache.query.QueryCursor;
import org.apache.ignite.cache.query.ScanQuery;
import org.apache.ignite.lang.IgniteBiPredicate;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import javax.cache.Cache;

@ShellComponent
public class ShellCommands {

    Ignite ignite;
    IgniteCache<Integer, Book> cache;


    public ShellCommands(Ignite ignite, IgniteCache<Integer, Book> cache) {
        this.ignite = ignite;
        this.cache = cache;
    }

    @ShellMethod("scanQuer")
    public void query(){
        ScanQuery<Integer, Book> scan = new ScanQuery<Integer, Book>(new CustomPredicate1());
        QueryCursor<Cache.Entry<Integer, Book>> cur = cache.query(scan);
        for (Cache.Entry<Integer, Book> e : cur) {
            System.out.println(e.getKey());
        }
    }

    private static class CustomPredicate1 implements IgniteBiPredicate<Integer, Book> {
        @Override
        public boolean apply(Integer i, Book book) {
            return i/1==2;
        }
    }
}
