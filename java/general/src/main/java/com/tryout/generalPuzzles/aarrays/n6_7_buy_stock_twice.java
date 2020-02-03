package com.tryout.generalPuzzles.aarrays;

import java.util.ArrayList;
import java.util.List;

public class n6_7_buy_stock_twice {

    public static double buyAndSellStockTwice(List<Double> prices) {
        double maxTotalProfit = 0.0;
        List<Double> firstBuySellProfits = new ArrayList<>();
        double minPriceSoFar = Double.MAX_VALUE;
        // Forward phase. For each day, we record maximum profit if we
        // sell on that day.
        for (int i = 0; i < prices.size(); ++i) {
            minPriceSoFar = Math.min(minPriceSoFar, prices.get(i));
            maxTotalProfit = Math.max(maxTotalProfit, prices.get(i) - minPriceSoFar);
            firstBuySellProfits.add(maxTotalProfit);
        }
        // Backward phase. For each day, find the maximum profit if we make
        // the second buy on that day.
        double maxPriceSoFar = Double.MIN_VALUE;
        for (int i = prices.size() - 1; i > 0; --i) {
            maxPriceSoFar = Math.max(maxPriceSoFar, prices.get(i));
            maxTotalProfit
                    = Math.max(maxTotalProfit, maxPriceSoFar - prices.get(i)
                    + firstBuySellProfits.get(i - 1));
        }
        return maxTotalProfit;
    }
}
