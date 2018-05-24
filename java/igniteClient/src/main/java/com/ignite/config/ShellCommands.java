package com.ignite.config;

import com.ignite.domain.Book;
import com.ignite.domain.Library;
import com.ignite.util.Utils;
import org.apache.ignite.*;
import org.apache.ignite.binary.BinaryObject;
import org.apache.ignite.cache.CacheAtomicityMode;
import org.apache.ignite.cache.CacheMode;
import org.apache.ignite.cache.CachePeekMode;
import org.apache.ignite.cache.CacheRebalanceMode;
import org.apache.ignite.cache.affinity.AffinityKey;
import org.apache.ignite.cache.query.QueryCursor;
import org.apache.ignite.cache.query.ScanQuery;
import org.apache.ignite.cluster.ClusterNode;
import org.apache.ignite.configuration.CacheConfiguration;
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
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.StreamSupport;

import static com.ignite.util.Utils.createDataSize;

@ShellComponent
public class ShellCommands {

    Ignite ignite;
    static String C = "test";

    @Autowired
    public ShellCommands(Ignite ignite) {
        this.ignite = ignite;
    }

    @ShellMethod("get from cache.")
    public String get(int a) {
        return ignite.cache(C).get(a).toString();
    }

    @ShellMethod("get from cache.")
    public void act() {
        ignite.cluster().active(true);
        //ignite.getOrCreateCache(C).put(13, "666");
    }

    @ShellMethod("get from cache.")
    public void act2() {
        ignite.cluster().active();
    }

    @ShellMethod("get from cache.")
    public void dact() {
        ignite.cluster().active(false);
    }

    @ShellMethod("get from cache.")
    public void checkact() {
        System.out.println(ignite.cluster().active());

    }

    @ShellMethod("get from cache.")
    public void showtopo() {
        ignite.cluster().currentBaselineTopology().stream().map(i-> i.consistentId().toString()).forEach(i-> System.out.println(i));
    }

    @ShellMethod("get from cache.")
    public void topo(int topVer) {
//        System.out.println("topo will be changed to nodes: ");
//        ignite.cluster().forServers().nodes().stream().forEach(i-> System.out.println(i.consistentId()));
//        ignite.cluster().setBaselineTopology(ignite.cluster().forServers().nodes());

        ignite.cluster().setBaselineTopology(topVer);
    }

    @ShellMethod("get from cache.")
    public void topo2() {
        System.out.println("topo will be changed to nodes: ");
        ignite.cluster().forServers().nodes().stream().forEach(i-> System.out.println(i.consistentId()));
        ignite.cluster().setBaselineTopology(ignite.cluster().forServers().nodes());
    }

    @ShellMethod("get from cache.")
    public void init() {
        CacheConfiguration<Integer, String> cfg = new CacheConfiguration<>();
        cfg.setName(C);
        cfg.setBackups(1);
        cfg.setRebalanceDelay(1000L);
        cfg.setCacheMode(CacheMode.PARTITIONED);
        cfg.setAtomicityMode(CacheAtomicityMode.ATOMIC);
        cfg.setRebalanceMode(CacheRebalanceMode.SYNC);
        IgniteCache cache = ignite.getOrCreateCache(cfg);

        IntStream.range(cache.size(CachePeekMode.ALL)+1, cache.size(CachePeekMode.ALL)+1+10).forEach(i -> {
                    cache.put(i, Utils.getRandonString(2));
                }
        );
    }

    @ShellMethod("get from cache.")
    public void ls (){
        Collection<String> lists = ignite.compute().broadcast(new IgniteCallable<String>() {
            @IgniteInstanceResource
            private Ignite ign;
            @Override
            public String call() throws Exception {
                IgniteCache<Integer, String> cache = ign.cache(C);
                return "server name: " + ign.cluster().localNode().consistentId() + C + " cache size: " + cache.localSize(CachePeekMode.ALL) + " entries: " +
                        StreamSupport.stream(cache.localEntries(CachePeekMode.PRIMARY).spliterator(), true).map(i->i.getKey().toString()).collect(Collectors.joining(",")) +
                        " backup entries: " +
                        StreamSupport.stream(cache.localEntries(CachePeekMode.BACKUP).spliterator(), true).map(i->i.getKey().toString()).collect(Collectors.joining(","));
            }
        });
        lists.forEach(i-> System.out.println(i));
    }



//    @ShellMethod("computeQuery")
//    public void test_stream(){
//        IgniteCache<AffinityKey, Book> bookC = ignite.getOrCreateCache("bookC");
//        IgniteCache<Integer, String> libC = ignite.getOrCreateCache("libC");
//        bookC.removeAll();
//        libC.removeAll();
//
//        System.out.println("bookC/libC cleaned size: " + bookC.size(CachePeekMode.ALL) + "/" + libC.size(CachePeekMode.ALL));
//        checkNodes(ignite);
//
//        libC.put(1,"libName1");
//        libC.put(2,"libName2");
//
//        List<Book> l = getBookList();
//
//        try(IgniteDataStreamer<AffinityKey, Book> stream = ignite.dataStreamer("bookC")){
//            stream.allowOverwrite(true);
//            stream.receiver(new StreamVisitor<AffinityKey, Book>(){
//                @IgniteInstanceResource
//                private Ignite ign;
//
//                @Override
//                public void apply(IgniteCache<AffinityKey, Book> cache, Map.Entry<AffinityKey, Book> e) {
//                    System.out.println("XYIXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
//
//                    String var1 = e.getValue().getId();
//                    String var2 = (String) ign.cache("libC").get(Integer.valueOf(e.getValue().getLibId()));
//
//                    com.ignite.config.ShellCommands.CustomClassLogic logic = new CustomClassLogic();
//
//                    System.out.println(logic.process(var1, var2));
//                }
//            });
//            l.forEach(e->stream.addData(e.key(), e));
//        }
//
//        System.out.println("total bookC/libC inited size: " + bookC.size(CachePeekMode.ALL) + "/" + libC.size(CachePeekMode.ALL));
//        checkNodes(ignite);
//    }
//
//    public static class CustomClassLogic{
//        public String process(String var1, String var2){
//            return "input_value/libName: " + var1 + "/" + var2;
//        }
//    }
//
//    private static List<Book> getBookList(){
//        List<Book> l = new ArrayList<>();
//        l.add(new Book("bookId1", "1", "auth1", "title1"));
//        l.add(new Book("bookId2", "1", "auth2", "title2"));
//        l.add(new Book("bookId3", "1", "auth3", "title3"));
//        l.add(new Book("bookId4", "2", "auth4", "title4"));
//        l.add(new Book("bookId5", "2", "auth5", "title5"));
//        l.add(new Book("bookId6", "2", "auth6", "title6"));
//        return l;
//    }
//    @ShellMethod("computeQuery")
//    public void test_simple(){
//        IgniteCache<AffinityKey, Book> bookC = ignite.getOrCreateCache("bookC");
//        IgniteCache<Integer, String> libC = ignite.getOrCreateCache("libC");
//
//        bookC.removeAll();
//        libC.removeAll();
//        System.out.println("bookC/libC cleaned size: " + bookC.size(CachePeekMode.ALL) + "/" + libC.size(CachePeekMode.ALL));
//        checkNodes(ignite);
//
//        libC.put(1,"v1");
//        libC.put(2,"v2");
//
//        List<Book> l = getBookList();
//
//        l.forEach(e->bookC.put(e.key(), e));
//
//        System.out.println("total bookC/libC inited size: " + bookC.size(CachePeekMode.ALL) + "/" + libC.size(CachePeekMode.ALL));
//        checkNodes(ignite);
//    }
//    public static void testAff(Ignite ignite){
//        //AffinityKey bookKey1 = new AffinityKey("myBookId1", "myLibId");
//        //AffinityKey bookKey2 = new AffinityKey("myBookId2", "myLibId");
//
//        //IgniteCache<Object, Library> libC = ignite.getOrCreateCache("libC");
//        //libC.put("myLibId", new Library("myLibId", "myLibrary"));
//
//        //bookC.put("myLibId", new Library("myLibId", "myLibrary"));
//        //bookC.put(bookKey1, new Book(bookKey1, "auth1", "title1"));
//        //bookC.put(bookKey2, new Book(bookKey2, "auth2", "title2"));
//    }
//
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
//    @ShellMethod("computeQuery")
//    public void exec(){
//        String s = affCall(1, "test");
//        System.out.println(s);
//    }
//
//    public String affCall(Integer key, String cacheName){
//        String s = ignite.compute().affinityCall(cacheName, key, new IgniteCallable<String>() {
//            @IgniteInstanceResource
//            private Ignite ignite;
//
//            @Override
//            public String call() throws Exception {
//                IgniteCache<Integer, Book> cache = ignite.getOrCreateCache("test");
//                Book b = cache.get(key);
//                return new CustomLogic().process(b);
//            }
//        });
//        return s;
//    }
//
//    public static class CustomLogic {
//        public String process(Book b){
//            return b.toString();
//        }
//    }
//////////////////////////////////////////////////
//
//    @ShellMethod("get from cache.")
//    public Book get(int a) {
//        return cache.get(a);
//    }
//
//    @ShellMethod("get from cache.")
//    public void initdummycache() {
//        IntStream.range(cache.size(CachePeekMode.ALL)+1, cache.size(CachePeekMode.ALL)+1+10).forEach(i -> {
//                    //cache.put(i, new Book("1",createDataSize(1024), Utils.getRandonString(2)));
//                }
//        );
//    }
//
//    @ShellMethod("computeQuery")
//    public void compute2(){
//        List<Integer> var = cache.query(new ScanQuery<Integer, Book>(
//                        // Remote filter.
//                        new CustomPredicate()),
//                // Transformer.
//                new CustomClosure()
//        ).getAll();
//
//        var.forEach(i-> System.out.println(i));
//    }
//
//    //works
//    @ShellMethod("computeQuery")
//    public void compute4(){
//        IgniteCompute compute = ignite.compute();
//        Collection<Integer> res = compute.apply(
//                new IgniteClosure<String, Integer>() {
//                    @Override
//                    public Integer apply(String word) {
//                        return word.length();
//                    }
//                },
//                Arrays.asList("Count characters using closure".split(" "))
//        );
//        res.forEach(i-> System.out.println(i));
//    }
//
//    //works
//    @ShellMethod("computeQuery")
//    public void compute3(){
//        Integer var = cache.query(new ScanQuery()).getAll().size();
//        System.out.println(var);
//    }
//
//    @ShellMethod("scanQuer")
//    public void mem2(){
//        Collection<Long> lists = ignite.compute().broadcast(new IgniteCallable<Long>() {
//
//            @IgniteInstanceResource
//            private Ignite ignite;
//
//            @Override
//            public Long call() throws Exception {
//                DataRegionMetrics regionsMetrics = ignite.dataRegionMetrics("Default_Region");
//                return regionsMetrics.getTotalAllocatedPages();
//            }
//        });
//        lists.forEach(i-> System.out.println(i));
//    }
//
//    @ShellMethod("scanQuer")
//    public void mem(){
//        DataRegionMetrics regionsMetrics = ignite.dataRegionMetrics("Default_Region");
//        System.out.println(">>> regionsMetrics.getTotalAllocatedPages(): " + regionsMetrics.getTotalAllocatedPages());
//
//    }
//
//    @ShellMethod("scanQuer")
//    public int size(){
//        return cache.size(CachePeekMode.ALL);
//    }
//
//    @ShellMethod("computeQuery")
//    public void compute(){
//        Collection<List<Integer>> lists = ignite.compute().broadcast(new IgniteCallable<List<Integer>>() {
//
//            @IgniteInstanceResource
//            private Ignite ignite;
//
//            @Override
//            public List<Integer> call() throws Exception {
//                IgniteCache<Integer, Book> c = ignite.cache("test");
//                return c.query(new ScanQuery<>(new CustomPredicate()).setLocal(true), new CustomClosure()).getAll();
//            }
//        });
//        List<Integer> res = new ArrayList<>(F.flatCollections(lists));
//        res.forEach(i-> System.out.println(i));
//    }
//
//    @ShellMethod("scanQuer")
//    public void query(){
//        ScanQuery<Integer, Book> scan = new ScanQuery<>(new CustomPredicate());
//        QueryCursor<Cache.Entry<Integer, Book>> cur = cache.query(scan);
//        for (Cache.Entry<Integer, Book> e : cur) {
//            System.out.println(e.getKey());
//        }
//    }
//
//    private static class CustomPredicate implements IgniteBiPredicate<Integer, Book> {
//        private static final long serialVersionUID = 1L;
//        @Override
//        public boolean apply(Integer i, Book book) {
//            return i/1==2;
//        }
//    }
//
//    private static class CustomClosure implements IgniteClosure<Cache.Entry<Integer, Book>, Integer> {
//        private static final long serialVersionUID = 1L;
//        @Override
//        public Integer apply(Cache.Entry<Integer, Book> integerBookEntry) {
//            return integerBookEntry.getKey();
//        }
//    }
//
//    @ShellMethod("scanQuer")
//    public void querybi(){
//        IgniteCache<Integer, BinaryObject> cache1 = ignite.getOrCreateCache("test").withKeepBinary();
//        ScanQuery<Integer, BinaryObject> scan = new ScanQuery<>(new CustomPredicateBi());
//        QueryCursor<Cache.Entry<Integer, BinaryObject>> cur = cache.query(scan);
//        for (Cache.Entry<Integer, BinaryObject> e : cur) {
//            System.out.println(e.getKey() + (String) e.getValue().field("title"));
//        }
//    }
//
//    private static class CustomPredicateBi implements IgniteBiPredicate<Integer, BinaryObject> {
//        private static final long serialVersionUID = 1L;
//        @Override
//        public boolean apply(Integer i, BinaryObject b) {
//            return i/1==2;
//        }
//    }
}
