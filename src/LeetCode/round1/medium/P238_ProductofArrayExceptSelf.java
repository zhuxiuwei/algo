package LeetCode.round1.medium;

import java.util.Arrays;

/**
 * 160914 - 0918
Given an array of n integers where n > 1, nums, return an array output such that output[i] is equal to the product of all the elements of nums except nums[i]. 
Solve it without division and in O(n). For example, given [1,2,3,4], return [24,12,8,6].

Follow up: Could you solve it with constant space complexity? (Note: The output array does not count as extra space for the purpose of space complexity analysis.)
 */
public class P238_ProductofArrayExceptSelf {

	/**
	 * AC: 25ms
	 * 思路巧妙。自己没想出来。从左到右遍历一遍算出每个元素左边的积，再从右到左遍历一遍算出每个元素右边的积和左边的积的积，就取得了结果。
	 * 参考： https://discuss.leetcode.com/topic/18864/simple-java-solution-in-o-n-without-extra-space
	 */
	public int[] productExceptSelf(int[] nums) {
		int n = nums.length;
	    int[] res = new int[n];

	    res[0] = 1;
	    
	    //calculate product elements on left
	    for (int i = 1; i < res.length; i++) 
			res[i] = nums[i - 1] * res[i - 1];	//now, res[i] stores the product of elements on nums[i]'s left

	    //calculate product elements on right
	    int right = nums[res.length - 1];
	    for (int i = res.length - 2; i >= 0; i--){
			res[i] *= right;
			right *= nums[i];
	    }
	    
		System.out.println(Arrays.toString(res));
	    return res;
	}

	//totally wrong. Waste lot of time.
	public int[] productExceptSelf_fail(int[] nums) {
		if (nums == null || nums.length <= 1)
			return null;
		else if (nums.length == 2) { // edge case
			int temp = nums[0];
			nums[0] = nums[1];
			nums[1] = temp;
		} else {
			int next = nums[1];
			for (int i = 1; i < nums.length - 1; i++) {
				for (int j = 0; j < i; j++) {
					nums[j] *= next;
				}
				nums[i + 1] = nums[i] * next;
				next = nums[i + 1];
			}
		}
		System.out.println(Arrays.toString(nums));
		return nums;
	}

	public static void main(String[] args) {
		P238_ProductofArrayExceptSelf p = new P238_ProductofArrayExceptSelf();
		p.productExceptSelf(new int[] { 1, 2 });
		p.productExceptSelf(new int[] { 1, 2, 3, 4, 5 });
	}

}
