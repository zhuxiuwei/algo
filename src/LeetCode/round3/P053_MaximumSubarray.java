package LeetCode.round3;

/**
 * 241016 medium
 * 做P918_MaximumSumCircularSubarray顺带解了，P918_MaximumSumCircularSubarray的简单版、【P918_MaximumSumCircularSubarray是数据需要考虑首位相接循环场景】
 * Given an integer array nums, find the   subarray with the largest sum, and return its sum.
 *
 * Example 1:
 * Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
 * Output: 6
 * Explanation: The subarray [4,-1,2,1] has the largest sum 6.
 *
 * Example 2:
 * Input: nums = [1]
 * Output: 1
 * Explanation: The subarray [1] has the largest sum 1.
 *
 * Example 3:
 * Input: nums = [5,4,-1,7,8]
 * Output: 23
 * Explanation: The subarray [5,4,-1,7,8] has the largest sum 23.
 *
 */
public class P053_MaximumSubarray {
    /**
     * AC: 1 ms Beats 99.42%, Memory 57.01 MB Beats 43.53%
     */
    public int maxSubArray(int[] nums) {
        int res = nums[0], sum = 0;
        for (int i = 0; i < nums.length; i++) {
            int n = nums[i];
            sum += n;
            res = Math.max(res, Math.max(n, sum));
            if (sum < 0) {
                sum = 0;
            }
        }
        return res;
    }
}
