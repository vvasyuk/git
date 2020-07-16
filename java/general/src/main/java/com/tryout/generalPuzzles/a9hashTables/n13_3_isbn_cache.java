package com.tryout.generalPuzzles.a9hashTables;

import java.util.LinkedHashMap;
import java.util.Map;

//The International Standard Book Number (ISBN) is a unique commercial book iden¬
//tifier. It is a string of length 10. The first 9 characters are digits; the last character
//is a check character. The check character is the sum of the first 9 digits, modulo 11,
//with 10 represented by 'X'. (Modern ISBNs use 13 digits, and the check digit is taken
//modulo 10; this problem is concerned with 10-digit ISBNs.)
//Create a cache for looking up prices of books identified by their ISBN. You implement
//lookup, insert, and remove methods. Use the Least Recently Used (LRU) policy for
//cache eviction. If an ISBN is already present, insert should not change the price, but
//it should update that entry to be the most recently used entry. Lookup should also
//update that entry to be the most recently used entry.
public class n13_3_isbn_cache {
    public class LRUCache {
        LinkedHashMap<Integer, Integer> isbnToPrice;

        LRUCache(final int capacity) {
            this.isbnToPrice = new LinkedHashMap<Integer, Integer>(capacity, 1.0f, true) {
                @Override
                protected boolean removeEldestEntry(Map.Entry<Integer, Integer> e) {
                    return this.size() > capacity;
                }
            };
        }

        public Integer lookup(Integer key) {
            if (!isbnToPrice.containsKey(key)) {
                return null;
            }
            return isbnToPrice.get(key);
        }

        public Integer insert(Integer key, Integer value) {
            // We add the value for key only if key is not present - we don’t update existing values.
            Integer currentValue = isbnToPrice.get(key);
            if (!isbnToPrice.containsKey(key)) {
                isbnToPrice.put(key, value);
                return currentValue;
            } else {
                return null;
            }
        }

        public Integer erase(Object key) {
            return isbnToPrice.remove(key);
        }
    }
}
