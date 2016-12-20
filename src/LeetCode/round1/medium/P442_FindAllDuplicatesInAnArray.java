package LeetCode.round1.medium;

import java.util.ArrayList;
import java.util.List;


/**
 * 161220
Given an array of integers, 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.
Find all the elements that appear twice in this array.
Could you do it without extra space and in O(n) runtime?

Example:
Input:
[4,3,2,7,8,2,3,1]
Output:
[2,3]
 */
public class P442_FindAllDuplicatesInAnArray {

	/**
	 * AC: 17ms, 65.37%.
	 * 思路是“448. Find All Numbers Disappeared in an Array”的变种。
	 * @param nums
	 * @return
	 */
	public List<Integer> findDuplicates(int[] nums) {
		List<Integer> res = new ArrayList<Integer>();
		for (int i = 0; i < nums.length; i++) {
			int idx = Math.abs(nums[i]) - 1;
			if(nums[idx] > 0)
				nums[idx] = -nums[idx];
			else
				res.add(idx + 1);
		}
		return res;
    }
	
	public static void main(String[] args) {
		P442_FindAllDuplicatesInAnArray p = new P442_FindAllDuplicatesInAnArray();
		System.out.println(p.findDuplicates(new int[]{4,3,2,7,8,2,3,1}));
	}

}
