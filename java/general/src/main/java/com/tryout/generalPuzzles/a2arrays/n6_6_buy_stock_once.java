package com.tryout.generalPuzzles.a2arrays;

public class n6_6_buy_stock_once {
    public static void main(String[] args) {
        int[] prices = new int[] {310,315, 275, 295, 260, 270, 290, 230, 255, 250};
        
        maxProfit(prices);
    }

    private static void maxProfit(int[] prices) {
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;

        for (int i = 0; i < prices.length; i++) {
            maxProfit = Math.max(maxProfit, prices[i]-minPrice);
            minPrice = Math.min(minPrice, prices[i]);
        }
        System.out.println("maxProfit: " + maxProfit);
    }
}
