package LeetCode.round1.hard;

import java.util.Arrays;

/**
 * 161010-12
Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive), prove that at least one duplicate number must exist. 
Assume that there is only one duplicate number, find the duplicate one.

Note:
You must not modify the array (assume the array is read only).
You must use only constant, O(1) extra space.
Your runtime complexity should be less than O(n^2).
There is only one duplicate number in the array, but it could be repeated more than once.
 */
public class P287_FindTheDuplicateNumber {

	
	
	/**
	 * （不符合条件的解法）
	 * Array sorted...so array is modified. O(Nlgn).... 6ms, 20.11%.
	 */
	public int findDuplicate_sort(int[] nums) {
		Arrays.sort(nums);
		for (int i = 0; i < nums.length - 1; i++) {
			if(nums[i] == nums[i+1])
				return nums[i];
		}
        return 0;
    }
	
	/**
	 * （不符合条件的解法）
	 * Straight forward O(N^2).... 96ms, 5.66%.
	 */
	public int findDuplicate_ON2(int[] nums) {
		for (int i = 0; i < nums.length; i++) {
			int t = nums[i];
			for (int j = i + 1; j < nums.length; j++) {
				if(nums[j] == t)
					return t;
			}
		}
        return 0;
    }
	
	public static void main(String[] args) {

	}

}
