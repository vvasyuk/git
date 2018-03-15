package com.ignite.config;

import com.ignite.domain.Book;
import com.ignite.domain.Library;
import com.ignite.util.Utils;
import org.apache.ignite.*;
import org.apache.ignite.binary.BinaryObject;
import org.apache.ignite.cache.CachePeekMode;
import org.apache.ignite.cache.query.QueryCursor;
import org.apache.ignite.cache.query.ScanQuery;
import org.apache.ignite.internal.util.typedef.F;
import org.apache.ignite.lang.IgniteBiInClosure;
import org.apache.ignite.lang.IgniteBiPredicate;
import org.apache.ignite.lang.IgniteCallable;
import org.apache.ignite.lang.IgniteClosure;
import org.apache.ignite.resources.IgniteInstanceResource;
import org.apache.ignite.stream.StreamReceiver;
import org.apache.ignite.stream.StreamVisitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import javax.cache.Cache;
import java.util.*;
import java.util.stream.IntStream;

import static com.ignite.util.Utils.createDataSize;

@ShellComponent
public class ShellCommands {

    Ignite ignite;
    IgniteCache<Integer, Book> cache;


    @Autowired
    public ShellCommands(Ignite ignite, IgniteCache<Integer, Book> cache) {
        this.ignite = ignite;
        this.cache = cache;
    }

    @ShellMethod("computeQuery")
    public void test_stream(){
        IgniteCache<Integer, String> bookC = ignite.getOrCreateCache("bookC");
        IgniteCache<Integer, String> bookD = ignite.getOrCreateCache("bookD");

        bookD.put(1,"v1");
        bookD.put(2,"v2");
        bookD.put(3,"v3");

        bookC.removeAll();
        System.out.println("bookC cleaned size: " + bookC.size(CachePeekMode.ALL));
        checkNodes(ignite);

        try(IgniteDataStreamer<Integer, String> stream = ignite.dataStreamer("bookC")){
            stream.allowOverwrite(true);
            stream.receiver(new StreamVisitor<Integer, String>(){
                @IgniteInstanceResource
                private Ignite ign;

                @Override
                public void apply(IgniteCache<Integer, String> cache, Map.Entry<Integer, String> e) {
                    System.out.println("XYIXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
                    System.out.println("input_key: " + e.getKey());
                    System.out.println("input_value: " + e.getValue());
                    //cache.put(e.getKey(), e.getValue());
                    IgniteCache<Integer, String> c = ign.getOrCreateCache("bookD");
                    String s = c.get(e.getKey());
                    System.out.println("value from another cache: " + s);
                }
            });
            stream.addData(2, "test");
        }

        System.out.println("bookC inited size: " + bookC.size(CachePeekMode.ALL));
        checkNodes(ignite);

    }

    public static void testSimple(Ignite ignite){
        IgniteCache<Integer, Object> bookC = ignite.getOrCreateCache("bookC");
        Book b1 = new Book("1", "auth1", "title1");
        Book b2 = new Book("1", "auth2", "title1");
        Book b3 = new Book("1", "auth3", "title1");
        Book b4 = new Book("1", "auth4", "title1");
        Book b5 = new Book("1", "auth5", "title1");

        bookC.put(1, b1);
        bookC.put(2, b2);
        bookC.put(3, b3);
        bookC.put(4, b4);
        bookC.put(5, b5);

        System.out.println("bookC size: " + bookC.size(CachePeekMode.ALL));
        checkNodes(ignite);

    }

    public static void checkNodes(Ignite ignite){
        Collection<Long> lists = ignite.compute().broadcast(new IgniteCallable<Long>() {

            @IgniteInstanceResource
            private Ignite ign;

            @Override
            public Long call() throws Exception {
                System.out.println("server name: " + ignite.cluster().localNode().id() + " cache size: " + ign.getOrCreateCache("bookC").localSize(CachePeekMode.ALL));

                return 1l;
            }
        });
        lists.forEach(i-> System.out.println(i));
    }

    public static void testAff(Ignite ignite){
        //AffinityKey bookKey1 = new AffinityKey("myBookId1", "myLibId");
        //AffinityKey bookKey2 = new AffinityKey("myBookId2", "myLibId");

        //IgniteCache<Object, Library> libC = ignite.getOrCreateCache("libC");
        //libC.put("myLibId", new Library("myLibId", "myLibrary"));

        //bookC.put("myLibId", new Library("myLibId", "myLibrary"));
        //bookC.put(bookKey1, new Book(bookKey1, "auth1", "title1"));
        //bookC.put(bookKey2, new Book(bookKey2, "auth2", "title2"));
    }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @ShellMethod("computeQuery")
    public void exec(){
        String s = affCall(1, "test");
        System.out.println(s);
    }

    public String affCall(Integer key, String cacheName){
        String s = ignite.compute().affinityCall(cacheName, key, new IgniteCallable<String>() {
            @IgniteInstanceResource
            private Ignite ignite;

            @Override
            public String call() throws Exception {
                IgniteCache<Integer, Book> cache = ignite.getOrCreateCache("test");
                Book b = cache.get(key);
                return new CustomLogic().process(b);
            }
        });
        return s;
    }

    public static class CustomLogic {
        public String process(Book b){
            return b.toString();
        }
    }
////////////////////////////////////////////////

    @ShellMethod("get from cache.")
    public Book get(int a) {
        return cache.get(a);
    }

    @ShellMethod("get from cache.")
    public void initdummycache() {
        IntStream.range(cache.size(CachePeekMode.ALL)+1, cache.size(CachePeekMode.ALL)+1+10).forEach(i -> {
                    //cache.put(i, new Book("1",createDataSize(1024), Utils.getRandonString(2)));
                }
        );
    }

    @ShellMethod("computeQuery")
    public void compute2(){
        List<Integer> var = cache.query(new ScanQuery<Integer, Book>(
                        // Remote filter.
                        new CustomPredicate()),
                // Transformer.
                new CustomClosure()
        ).getAll();

        var.forEach(i-> System.out.println(i));
    }

    //works
    @ShellMethod("computeQuery")
    public void compute4(){
        IgniteCompute compute = ignite.compute();
        Collection<Integer> res = compute.apply(
                new IgniteClosure<String, Integer>() {
                    @Override
                    public Integer apply(String word) {
                        return word.length();
                    }
                },
                Arrays.asList("Count characters using closure".split(" "))
        );
        res.forEach(i-> System.out.println(i));
    }

    //works
    @ShellMethod("computeQuery")
    public void compute3(){
        Integer var = cache.query(new ScanQuery()).getAll().size();
        System.out.println(var);
    }

    @ShellMethod("scanQuer")
    public void mem2(){
        Collection<Long> lists = ignite.compute().broadcast(new IgniteCallable<Long>() {

            @IgniteInstanceResource
            private Ignite ignite;

            @Override
            public Long call() throws Exception {
                DataRegionMetrics regionsMetrics = ignite.dataRegionMetrics("Default_Region");
                return regionsMetrics.getTotalAllocatedPages();
            }
        });
        lists.forEach(i-> System.out.println(i));
    }

    @ShellMethod("scanQuer")
    public void mem(){
        DataRegionMetrics regionsMetrics = ignite.dataRegionMetrics("Default_Region");
        System.out.println(">>> regionsMetrics.getTotalAllocatedPages(): " + regionsMetrics.getTotalAllocatedPages());

    }

    @ShellMethod("scanQuer")
    public int size(){
        return cache.size(CachePeekMode.ALL);
    }

    @ShellMethod("computeQuery")
    public void compute(){
        Collection<List<Integer>> lists = ignite.compute().broadcast(new IgniteCallable<List<Integer>>() {

            @IgniteInstanceResource
            private Ignite ignite;

            @Override
            public List<Integer> call() throws Exception {
                IgniteCache<Integer, Book> c = ignite.cache("test");
                return c.query(new ScanQuery<>(new CustomPredicate()).setLocal(true), new CustomClosure()).getAll();
            }
        });
        List<Integer> res = new ArrayList<>(F.flatCollections(lists));
        res.forEach(i-> System.out.println(i));
    }

    @ShellMethod("scanQuer")
    public void query(){
        ScanQuery<Integer, Book> scan = new ScanQuery<>(new CustomPredicate());
        QueryCursor<Cache.Entry<Integer, Book>> cur = cache.query(scan);
        for (Cache.Entry<Integer, Book> e : cur) {
            System.out.println(e.getKey());
        }
    }

    private static class CustomPredicate implements IgniteBiPredicate<Integer, Book> {
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
