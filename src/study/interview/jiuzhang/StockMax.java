package study.interview.jiuzhang;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 * [《Amazon HackerRank OA 面经》](http://www.jiuzhang.com/qa/748/) 170529  
Your algorithms have become so good at predicting the market that you now know what the share price of Wooden Orange Toothpicks Inc. (WOT) will be for the next N days.
Each day, you can either buy one share of WOT, sell any number of shares of WOT that you own, or not make any transaction at all. What is the maximum profit you can obtain with an optimum trading strategy?
Example 1: 5 3 2 => 0. you cannot obtain any profit because the share price never rises. 
Example 2: 1 2 100 => 197. you can buy one share on the first two days, and sell both of them on the third day. 
Example 3: 1 3 1 2 => 3. you can buy one share on day 1, sell one on day 2, buy one share on day 3, and sell one share on day 4.
 */
public class StockMax {

	public static int maxProfit(int[] prices){
		if(prices == null || prices.length == 0)
			return 0;
		int res = 0;
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>(Collections.reverseOrder());	//利用大根堆
		for (int i = 0; i < prices.length; i++) 
			pq.add(prices[i]);
		
		for (int i = 0; i < prices.length; i++) {
			if(prices[i] < pq.peek()){
				res += pq.peek() - prices[i];
			}else{
				pq.poll();
			}
		}
		return res;
	}
	
	public static void main(String[] args) {
		System.out.println(maxProfit(new int[]{5,3,2}));	//0
		System.out.println(maxProfit(new int[]{1,2,100,18,32}));	//197+14=211
		System.out.println(maxProfit(new int[]{1,3,1,2}));	//3
	}

}
