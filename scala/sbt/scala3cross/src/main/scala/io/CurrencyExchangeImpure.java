package io;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class CurrencyExchangeImpure {
    static Map<String, BigDecimal> exchangeRatesTableApiCall(String currency) {
        Random rand = new Random();
        if (rand.nextFloat() < 0.25) throw new RuntimeException("Connection error");
        HashMap<String, BigDecimal> result = new HashMap<String, BigDecimal>();
        if(currency.equals("USD")) {
            result.put("EUR", BigDecimal.valueOf(0.81 + (rand.nextGaussian() / 100)).setScale(2, RoundingMode.FLOOR));
            result.put("JPY", BigDecimal.valueOf(103.25 + (rand.nextGaussian())).setScale(2, RoundingMode.FLOOR));
            return result;
        }
        throw new RuntimeException("Rate not available");
    }
}
