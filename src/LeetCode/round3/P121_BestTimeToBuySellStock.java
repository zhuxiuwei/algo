package LeetCode.round3;

/**
 * ou are given an array prices where prices[i] is the price of a given stock on the ith day.
 * You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the future to sell that stock.
 * Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0.
 *
 * Example 1:
 * Input: prices = [7,1,5,3,6,4]
 * Output: 5
 * Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
 * Note that buying on day 2 and selling on day 1 is not allowed because you must buy before you sell.
 *
 * Example 2:
 * Input: prices = [7,6,4,3,1]
 * Output: 0
 * Explanation: In this case, no transactions are done and the max profit = 0.
 */
public class P121_BestTimeToBuySellStock {

    /**
     * 没什么难度。 O(n)
     * AC: 70.28%, mem: 87.5%
     */
    public int maxProfit(int[] prices) {
        if(prices == null || prices.length <= 1)
            return 0;
        int res = 0, previousMin = prices[0];
        for (int i = 1; i < prices.length; i++) {
            if(prices[i] - previousMin > res)
                res = prices[i] - previousMin;
            if(prices[i] < previousMin)
                previousMin = prices[i];
        }
        return res;
    }

    /**
     * 暴力O(n^2)解法。
     * 超时了。
     */
    public int maxProfit_timeout(int[] prices) {
        if(prices == null || prices.length <= 1)
            return 0;
        int res = 0;
        for (int i = 0; i < prices.length; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                if(prices[j] - prices[i] > res)
                    res = prices[j] - prices[i];
            }
        }
        return res;
    }

    public static void main(String[] args) {
        P121_BestTimeToBuySellStock p = new P121_BestTimeToBuySellStock();
        System.out.println(p.maxProfit(new int[]{7,1,5,3,6,4}));   //5
        System.out.println(p.maxProfit(new int[]{7,6,4,3,1}));   //0
    }
}
