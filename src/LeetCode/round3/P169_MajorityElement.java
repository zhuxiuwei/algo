package LeetCode.round3;

/**
 * 240907 easy
 * Given an array nums of size n, return the majority element.
 * The majority element is the element that appears more than ⌊n / 2⌋ times. You may assume that the majority element always exists in the array.
 *
 * Example 1:
 * Input: nums = [3,2,3]
 * Output: 3
 *
 * Example 2:
 * Input: nums = [2,2,1,1,1,2,2]
 * Output: 2
 *
 * Constraints:
 * n == nums.length
 * 1 <= n <= 50000
 * -10^9 <= nums[i] <= 10^9
 */
public class P169_MajorityElement {

    /**
     * O(1) space的最佳思路想不到，看了16采纳的方案。 https://discuss.leetcode.com/topic/8692/o-n-time-o-1-space-fastest-solution
     * AC: Runtime 1ms Beats 99.79%, Memory 52.66MB Beats 68.70%
     */
    public int majorityElement(int[] nums) {
        int curMax = nums[0], count = 1;
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] == curMax){
                count ++;
            }else {
                count --;
            }
            if(count == 0){ //更换curMax
                curMax = nums[i];
                count ++;
            }
        }
        return curMax;
    }
}
