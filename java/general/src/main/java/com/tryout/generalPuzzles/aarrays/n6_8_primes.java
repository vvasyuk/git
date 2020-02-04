package com.tryout.generalPuzzles.aarrays;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class n6_8_primes {
    public static void main(String[] args) {

    }

    // Given n, return all primes up to and including n.
    public static List<Integer> generatePrimes(int n) {
        List<Integer> primes = new ArrayList<>();
        // isPrime.get(p) represents if p is prime or not. Initially , set each
        // to true, excepting 0 and 1. Then use sieving to eliminate nonprimes.
        List<Boolean> isPrime = new ArrayList<>(Collections.nCopies(n + 1, true));
        isPrime.set(0 , false);
        isPrime.set(1, false);
        for (int p = 2; p <= n; ++p) {
            if (isPrime.get(p)) {
                primes.add(p);
        // Sieve p’s multiples.
                for (int j = p; j <= n; j += p) {
                    isPrime.set(j, false);
                }
            }
        }
        return primes;
    }
}
