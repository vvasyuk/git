package com.example.demo.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jopa on 11/8/2017.
 */
public class ThreadAttributes {
    private static ThreadLocal<Map<String, Integer>> threadAttrs = new ThreadLocal<Map<String, Integer>>() {
        @Override
        protected Map<String, Integer> initialValue() {
            return new HashMap<String, Integer>();
        }
    };

    public static Integer get(String key) {
        return threadAttrs.get().get(key);
    }

    public static void set(String key, Integer value) {
        threadAttrs.get().put(key, value);
    }
}
