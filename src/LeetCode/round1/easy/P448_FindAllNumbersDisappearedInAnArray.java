package LeetCode.round1.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * 161220
Given an array of integers where 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.
Find all the elements of [1, n] inclusive that do not appear in this array.
Could you do it without extra space and in O(n) runtime? You may assume the returned list does not count as extra space.

Example:
Input:
[4,3,2,7,8,2,3,1]
Output:
[5,6]
 */
public class P448_FindAllNumbersDisappearedInAnArray {

	/**
	 * 思路参考的 https://discuss.leetcode.com/topic/65738/java-accepted-simple-solution
	 * AC: 19ms, 0.22%，不知道为何这么慢。 
	 */
	public List<Integer> findDisappearedNumbers(int[] nums) {
		List<Integer> res = new ArrayList<Integer>();
		for (int i = 0; i < nums.length; i++) {
			int val = Math.abs(nums[i]) - 1;
			if(nums[val] > 0)
				nums[val] = -nums[val];
		}
		for (int i = 0; i < nums.length; i++) {
			if(nums[i] > 0)
				res.add( i + 1);
		}
		return res;
	}
	
	public static void main(String[] args) {
		P448_FindAllNumbersDisappearedInAnArray p = new P448_FindAllNumbersDisappearedInAnArray();
		System.out.println(p.findDisappearedNumbers(new int[]{4,3,2,7,8,2,3,1}));
	}

}
