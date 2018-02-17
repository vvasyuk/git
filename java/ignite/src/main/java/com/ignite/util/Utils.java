package com.ignite.util;

import org.apache.commons.text.CharacterPredicates;
import org.apache.commons.text.RandomStringGenerator;

import java.time.Duration;
import java.time.Instant;

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

    public static void methodElapsedTime(Runnable toRun) {
        Instant a = Instant.now();
        toRun.run();
        //String s = (String) toRun;
        Instant b = Instant.now();
        Duration timeElapsed = Duration.between(a, b);
        System.out.println(" | elapsed time : " + timeElapsed);
    }
}
