package LeetCode.round1.medium;
/**
 * 161213
Say you have an array for which the ith element is the price of a given stock on day i.
Design an algorithm to find the maximum profit. You may complete as many transactions as you like (ie, buy one and sell one share of the stock multiple times) with the following restrictions:

You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
After you sell your stock, you cannot buy stock on next day. (ie, cooldown 1 day)
Example:

prices = [1, 2, 3, 0, 2]
maxProfit = 3
transactions = [buy, sell, cooldown, buy, sell]
 */
public class P309_BestTimeToBuyAndSellStockWithCooldown {

	/**
	 * 不会。DP问题。Refer: https://discuss.leetcode.com/topic/30421/share-my-thinking-process
	 */
	public int maxProfit(int[] prices){
		int sell = 0, prev_sell = 0, buy = Integer.MIN_VALUE, prev_buy;
	    for (int price : prices) {
	        prev_buy = buy;
	        buy = Math.max(prev_sell - price, prev_buy);
	        prev_sell = sell;
	        sell = Math.max(prev_buy + price, prev_sell);
	    }
	    return sell;
	}
	
	public static void main(String[] args) {
		P309_BestTimeToBuyAndSellStockWithCooldown p = new P309_BestTimeToBuyAndSellStockWithCooldown();
		System.out.println(p.maxProfit(new int[]{10,18,26,1,20,1,15}));	//30 (10-26, 1-15) 
		System.out.println(p.maxProfit(new int[]{10,18,26,1,20,1,15,1,6}));	//32 (10-18, 1-20, 1-6)
		System.out.println(p.maxProfit(new int[]{1,3,5,4,100}));	//99 (1-100)
	}

}
