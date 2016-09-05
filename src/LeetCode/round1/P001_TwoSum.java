package LeetCode.round1;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Difficulty: Easy
Given an array of integers, return indices of the two numbers such that they add up to a specific target.
You may assume that each input would have exactly one solution.

Example:
Given nums = [2, 7, 11, 15], target = 9,

Because nums[0] + nums[1] = 2 + 7 = 9,
return [0, 1].
UPDATE (2016/2/13):
The return format had been changed to zero-based indices. Please read the above updated description carefully.
 *
 */
public class P001_TwoSum {
	public int[] twoSum(int[] nums, int target) {
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i = 0; i < nums.length; i++) {
			if(map.containsKey(target - nums[i])){
				int[] res = new int[2];
				res[0] = map.get(target - nums[i]);
				res[1] = i;
				return res;
			}
			map.put(nums[i], i);	//!!!!!!!!!!Note bug here: If move map.put before 'if' @ line 23, will fail test case: {3,2,4}, 6
		}
		return null;
    }
	
	public static void main(String[] args) {
		P001_TwoSum p = new P001_TwoSum();
		int[] nums = {3,2,4};
		int target = 6;
		System.out.println(Arrays.toString(p.twoSum(nums, target)));
	}
}
