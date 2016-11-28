package LeetCode.round1.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 161128
Given an array S of n integers, are there elements a, b, c, and d in S such that a + b + c + d = target? 
Find all unique quadruplets in the array which gives the sum of target. Note: The solution set must not contain duplicate quadruplets.

For example, given array S = [1, 0, -1, 0, -2, 2], and target = 0.
A solution set is:
[
  [-1,  0, 0, 1],
  [-2, -1, 1, 2],
  [-2,  0, 0, 2]
]
 */
public class P018_4Sum {

	public List<List<Integer>> fourSum(int[] nums, int target) {
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		if(nums == null || nums.length < 4)
			return res;
		Arrays.sort(nums);
		int min = 0, max = 0;
		for (int i = 0; i < 4; i++) {
			min += nums[i];
			max += nums[nums.length - 1 - i];
		}
		if(target < min || target > max)
			return res;
		
		
		
		return res;
    }
	
	public static void main(String[] args) {
		P018_4Sum p = new P018_4Sum();
		System.out.println(p.fourSum(new int[]{1, 0, -1, 0, -2, 2},  0));
	}

}
