package LeetCode.round1.medium;

import java.util.Arrays;

/**
 * 161011
Given a non-empty array containing only positive integers, find if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal.

Note:
Both the array size and each of the array element will not exceed 100.

Example 1:
Input: [1, 5, 11, 5]
Output: true
Explanation: The array can be partitioned as [1, 5, 5] and [11].

Example 2:
Input: [1, 2, 3, 5]
Output: false
Explanation: The array cannot be partitioned into equal sum subsets.
 */
public class P416_PartitionEqualSubsetSum {

	/**
	 * 很奇怪的问题，感觉test case有错。已经提问了: https://discuss.leetcode.com/topic/62630/need-help-super-wired-test-cases-are-there-wrong-test-cases
	 */
	
	/**
	 * TODO: Try DFS solution.
	 */
	
	/**
	 * Greedy. Fail. Fail on like {2 ,2, 2, 1, 2, 3}
	 * 其实这个问题就是类似0-1背包问题。 0-1背包问题是不能greedy解决的，要用DP。 分数背包问题可以被Greedy解决。
	 */
	public boolean canPartition_greedy_fail(int[] nums) {
		if(nums == null)
			return false;
		int sum = 0;
		
		for (int i: nums) 
			sum += i;
		if(sum % 2 != 0)
			return false;
		
		Arrays.sort(nums);
		
		int target = sum / 2;
		int left = 0, right = nums.length - 1;
		while(target > 0 && left <= right){
			if(target >= nums[right]){
				target -= nums[right];
				right --;
			}else{
				target -= nums[left];
				left ++;
			}
		}
        return target == 0;
    }
	
	/**
	 * AC: 7ms
	 * ---------- LeetCode test case bug???? ---------- 
	 * {1, 3, 4, 4}, {2, 2, 3, 5} both should return false, but this function return true and AC expected true. 
	 */
	public boolean canPartition_ShouldBeWrongButACed(int[] nums) {
		if(nums == null)
			return false;
		int sum = 0, max = 0;
		for (int i: nums) {
			sum += i;
			if(i > max)
				max = i;
		}
		if(sum % 2 != 0)
			return false;
        return max <= sum/2;
    }
	
	public static void main(String[] args) {
		P416_PartitionEqualSubsetSum p = new P416_PartitionEqualSubsetSum();
		System.out.println(p.canPartition_greedy_fail(new int[]{1, 5, 11, 5}));	//true
		System.out.println(p.canPartition_greedy_fail(new int[]{1, 2, 3, 5}));	//false
		System.out.println(p.canPartition_greedy_fail(new int[]{2, 2, 3, 5}));	//false
		System.out.println(p.canPartition_greedy_fail(new int[]{1, 3, 4, 4}));	//false
		System.out.println(p.canPartition_greedy_fail(new int[]{1, 2, 3, 4, 5, 6, 7}));	//true
		System.out.println(p.canPartition_greedy_fail(new int[]{28,63,95,30,39,16,36,44,37,100,61,73,32,71,100,2,37,60,23,71,53,70,69,82,97,43,16,33,29,5,97,32,29,78,93,59,37,88,89,79,75,9,74,32,81,12,34,13,16,15,16,40,90,70,17,78,54,81,18,92,75,74,59,18,66,62,55,19,2,67,30,25,64,84,25,76,98,59,74,87,5,93,97,68,20,58,55,73,74,97,49,71,42,26,8,87,99,1,16,79})); //true
		System.out.println(p.canPartition_greedy_fail(new int[]{1, 2, 3, 4, 5, 6, 7}));	//true

	}

}
