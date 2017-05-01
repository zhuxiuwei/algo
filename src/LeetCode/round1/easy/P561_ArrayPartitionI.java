package LeetCode.round1.easy;

import java.util.Arrays;

/**
 * 170501
Given an array of 2n integers, your task is to group these integers into n pairs of integer, say (a1, b1), (a2, b2), ..., (an, bn) which makes sum of min(ai, bi) for all i from 1 to n as large as possible.

Example 1:
Input: [1,4,3,2]

Output: 4
Explanation: n is 2, and the maximum sum of pairs is 4.
Note:
n is a positive integer, which is in the range of [1, 10000].
All the integers in the array will be in the range of [-10000, 10000].
 */
public class P561_ArrayPartitionI {
	
	/**
	 * 1 time AC: 39.62%
	 */
	public int arrayPairSum(int[] nums) {
		if(nums == null || nums.length == 0)
			return 0;
		
        Arrays.sort(nums);
        int sum = 0;
        for (int i = 0; i < nums.length; i += 2) {
			sum += nums[i];
		}
        return sum;
    }
	
	public static void main(String[] args) {
		P561_ArrayPartitionI p = new P561_ArrayPartitionI();
		System.out.println(p.arrayPairSum(new int[]{1,4,3,2}));	//4
	}

}
