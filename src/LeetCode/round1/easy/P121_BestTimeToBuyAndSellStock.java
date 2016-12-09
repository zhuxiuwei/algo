package LeetCode.round1.easy;
/**
 * 160917 
Say you have an array for which the ith element is the price of a given stock on day i.
If you were only permitted to complete at most one transaction (ie, buy one and sell one share of the stock), design an algorithm to find the maximum profit.

Example 1:
Input: [7, 1, 5, 3, 6, 4]
Output: 5
max. difference = 6-1 = 5 (not 7-1 = 6, as selling price needs to be larger than buying price)

Example 2:
Input: [7, 6, 4, 3, 1]
Output: 0
In this case, no transaction is done, i.e. max profit = 0.
 */
public class P121_BestTimeToBuyAndSellStock {

	/**
	 * Use array. AC: 2ms， 48.6%
	 * Time: O(n). Space: O(n).
	 * Note: 1 bug.
	 */
	public int maxProfit(int[] prices) {
		if(prices == null || prices.length <= 1)
			return 0;
		
		int tmpMax = 0, max = 0;
		int[] diff = new int[prices.length - 1];
		for (int i = 0; i < diff.length; i++) 
			diff[i] = prices[i + 1] - prices[i];

		for (int i = 0; i < diff.length; i++) {
			if(tmpMax > max)
				max = tmpMax;
			tmpMax += diff[i];
			if(tmpMax <= 0){
				tmpMax = 0;
				continue;
			}
		}
		
		if(tmpMax > max) max = tmpMax;	//!!!!!Note bug here: If not add this line, will fail such scenario: [1, 2]
			
        return max;
    }
			
	public static void main(String[] args) {
		P121_BestTimeToBuyAndSellStock p = new P121_BestTimeToBuyAndSellStock();
		System.out.println(p.maxProfit(new int[]{1,2}));
		System.out.println(p.maxProfit(new int[]{7, 1, 5, 3, 6, 4}));
	}

}
/**
 * One bug:  If not add that line, will fail such scenario: [1, 2]
 */
