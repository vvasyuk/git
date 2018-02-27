package com.ignite.util;

import com.ignite.domain.Book;
import org.apache.commons.text.CharacterPredicates;
import org.apache.commons.text.RandomStringGenerator;
import org.apache.ignite.IgniteCache;

import java.time.Duration;
import java.time.Instant;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;

/**
 * Created by Jopa on 2/17/2018.
 */
public class Utils {

    public static String getRandonString(int i){
        RandomStringGenerator randomStringGenerator =
                new RandomStringGenerator.Builder()
                        .withinRange('0', 'z')
                        .filteredBy(CharacterPredicates.LETTERS, CharacterPredicates.DIGITS)
                        .build();
        return randomStringGenerator.generate(8);
    }

    public static Duration cacheGetWrapper(BiFunction<IgniteCache<Integer, Book>,Integer, Duration> iTimeElapsed, IgniteCache<Integer, Book> cache, int idx) {
        return iTimeElapsed.apply(cache, idx);
    }

    public static BiFunction<IgniteCache<Integer, Book>,Integer, Duration> elapsedTimeWrapper = (cache, idx) -> {
        Instant a = Instant.now();
        Book s = (Book) cache.get(idx);
        Instant b = Instant.now();
        Duration timeElapsed = Duration.between(a, b);
        System.out.println(s + " | elapsed time to get : " + timeElapsed);
        return timeElapsed;
    };


//Option1
//    public static void cacheGetWrapper(ITimeElapsed iTimeElapsed, IgniteCache<Object, Object> cache, int idx) {
//        iTimeElapsed.elapsedTimeWrapper(cache, idx);
//    }
//
//    public interface ITimeElapsed {
//        public void elapsedTimeWrapper(IgniteCache<Object, Object> cache, int i);
//    }
//
//    public static ITimeElapsed elapsedTimeWrapper = (cache, idx) -> {
//        Instant a = Instant.now();
//        String s = (String) cache.get(idx);
//        Instant b = Instant.now();
//        Duration timeElapsed = Duration.between(a, b);
//        System.out.println(s + " | elapsed time : " + timeElapsed);
//    };

//Option2
//    public static void methodElapsedTime(Runnable toRun) {
//        Instant a = Instant.now();
//        toRun.run();
//        //String s = (String) toRun;
//        Instant b = Instant.now();
//        Duration timeElapsed = Duration.between(a, b);
//        System.out.println(" | elapsed time : " + timeElapsed);
//    }

//Option3
//    public static void cacheGetWrapper(BiConsumer<IgniteCache<Object, Object>,Integer> iTimeElapsed, IgniteCache<Object, Object> cache, int idx) {
//        iTimeElapsed.accept(cache, idx);
//    }
//
//    public static BiConsumer<IgniteCache<Object, Object>,Integer> elapsedTimeWrapper = (cache, idx) -> {
//        Instant a = Instant.now();
//        String s = (String) cache.get(idx);
//        Instant b = Instant.now();
//        Duration timeElapsed = Duration.between(a, b);
//        System.out.println(s + " | elapsed time : " + timeElapsed);
//    };
}
