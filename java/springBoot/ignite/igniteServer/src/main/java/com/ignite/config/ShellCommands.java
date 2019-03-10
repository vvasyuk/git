//package com.ignite.config;
//
//import com.ignite.domain.Book;
//import com.ignite.util.Utils;
//import org.apache.ignite.DataRegionMetrics;
//import org.apache.ignite.Ignite;
//import org.apache.ignite.IgniteCache;
//import org.apache.ignite.IgniteCompute;
//import org.apache.ignite.binary.BinaryObject;
//import org.apache.ignite.cache.CachePeekMode;
//import org.apache.ignite.cache.query.QueryCursor;
//import org.apache.ignite.cache.query.ScanQuery;
//import org.apache.ignite.internal.util.typedef.F;
//import org.apache.ignite.lang.IgniteBiPredicate;
//import org.apache.ignite.lang.IgniteCallable;
//import org.apache.ignite.lang.IgniteClosure;
//import org.apache.ignite.resources.IgniteInstanceResource;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Profile;
//import org.springframework.shell.standard.ShellComponent;
//import org.springframework.shell.standard.ShellMethod;
//
//import javax.cache.Cache;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Collection;
//import java.util.List;
//import java.util.stream.IntStream;
//
//import static com.ignite.util.Utils.createDataSize;
//
//@ShellComponent
//public class ShellCommands {
//
//    Ignite ignite;
//    //IgniteCache<Integer, Book> cache;
//
//
//    @Autowired
//    public ShellCommands(Ignite ignite
//            //, IgniteCache<Integer, Book> cache
//    ) {
//        this.ignite = ignite;
//        //this.cache = cache;
//    }
//
//    @ShellMethod("scanQuer")
//    public int size(){
//        return cache.size(CachePeekMode.ALL);
//    }
//
//    @ShellMethod("get from cache.")
//    public Book get(int a) {
//        return cache.get(a);
//    }
//
//    @ShellMethod("get from cache.")
//    public void initdummycache() {
//        IntStream.range(cache.size(CachePeekMode.ALL)+1, cache.size(CachePeekMode.ALL)+1+10).forEach(i -> {
//                    cache.put(i, new Book(createDataSize(1024), Utils.getRandonString(2)));
//                }
//        );
//    }
//}
