package LeetCode.round1.easy;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

/**
170511
Given scores of N athletes, find their relative ranks and the people with the top three highest scores, who will be awarded medals: "Gold Medal", "Silver Medal" and "Bronze Medal".

Example 1:
Input: [5, 4, 3, 2, 1]
Output: ["Gold Medal", "Silver Medal", "Bronze Medal", "4", "5"]
Explanation: The first three athletes got the top three highest scores, so they got "Gold Medal", "Silver Medal" and "Bronze Medal". 
For the left two athletes, you just need to output their relative ranks according to their scores.
Note:
N is a positive integer and won't exceed 10,000.
All the scores of athletes are guaranteed to be unique.
 */
public class P506_RelativeRanks {

	/**
	 * AC: 26ms, 57.98%
	 * @param nums
	 * @return
	 */
	public String[] findRelativeRanks(int[] nums) {
		if(nums == null || nums.length == 0)
			return null;
		
		String[] res = new String[nums.length];
		Queue<Integer> prq = new PriorityQueue<Integer>(Collections.reverseOrder());
		for (int i = 0; i < nums.length; i++) 
			prq.offer(nums[i]);
		Map<Integer, Integer> numToSeq = new HashMap<Integer, Integer>();
		for (int i = 0; i < nums.length; i++) 
			numToSeq.put(prq.poll(), i);
		for (int i = 0; i < nums.length; i++) {
			int seq = numToSeq.get(nums[i]);
			switch(seq){
				case 0:
					res[i] = "Gold Medal";
					break;
				case 1:
					res[i] = "Silver Medal";
					break;
				case 2:
					res[i] = "Bronze Medal";
					break;
				default:
					res[i] = (seq + 1) + "";
					break;
			}
		}
		return res;
    }
	
	public static void main(String[] args) {
		P506_RelativeRanks p = new P506_RelativeRanks();
		System.out.println(Arrays.toString(p.findRelativeRanks(new int[]{5, 8, 3, 2, 12})));
		System.out.println(Arrays.toString(p.findRelativeRanks(new int[]{10,3,8,9,4})));
	}

}
