package LeetCode.round1.medium;
/**
 * 160917 
Say you have an array for which the ith element is the price of a given stock on day i.
Design an algorithm to find the maximum profit. You may complete as many transactions as you like (ie, buy one and sell one share of the stock multiple times). 
However, you may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
 */
public class P122_BestTimeToBuyAndSellStockII {

	/**
	 * Greedy. AC: 1ms
	 * Time: O(n). Space: O(n).
	 */
	public int maxProfit(int[] prices) {
		if(prices == null || prices.length <= 1)
			return 0;
		
		int sum = 0;
		int[] diff = new int[prices.length - 1];
		for (int i = 0; i < diff.length; i++) 
			diff[i] = prices[i + 1] - prices[i];

		for (int i = 0; i < diff.length; i++) 
			if(diff[i] > 0)
				sum += diff[i];
			
        return sum;		
    }
	
			
	public static void main(String[] args) {
		P122_BestTimeToBuyAndSellStockII p = new P122_BestTimeToBuyAndSellStockII();
		System.out.println(p.maxProfit(new int[]{1,2}));
		System.out.println(p.maxProfit(new int[]{7, 1, 5, 3, 6, 4}));
	}

}

/**
 * One time AC. 
 */
