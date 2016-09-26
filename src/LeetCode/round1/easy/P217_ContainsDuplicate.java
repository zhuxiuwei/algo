package LeetCode.round1.easy;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 160926
Given an array of integers, find if the array contains any duplicates. Your function should return true if any value appears at least twice in the array, and it should return false if every element is distinct.
 */
public class P217_ContainsDuplicate {

	/**
	 * hash. AC: 9ms. 67%.
	 * Time: O(n), Space: O(n).
	 */
	public boolean containsDuplicate_hash(int[] nums) {
		Set<Integer> s = new HashSet<Integer>();
		for(int i : nums)
			 if(!s.add(i))// if there is same
				 return true; 
		return false;
		
		//belwo is slower: 13ms. 48%.
//		Set<Integer> s = new HashSet<Integer>();
//		for(int i: nums)
//			s.add(i);
//	    return nums.length != s.size();
    }

	/**
	 * sort. AC: 11ms. 62%.
	 * Time: O(nlgn), Space: O(1).
	 */
	public boolean containsDuplicate_sort(int[] nums) {
		Arrays.sort(nums);
		for (int i = 0; i < nums.length - 1; i++) 
			if(nums[i] == nums[i + 1])
				return true;
		return false;
    }
	
}
