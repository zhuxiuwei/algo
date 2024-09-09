package LeetCode.round3;

/**
 * 240909 medium
 * https://leetcode.com/problems/longest-increasing-subsequence/description/?envType=study-plan-v2&envId=top-100-liked
 * Given an integer array nums, return the length of the longest strictly increasing subsequence
 *
 * Example 1:
 * Input: nums = [10,9,2,5,3,7,101,18]
 * Output: 4
 * Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
 *
 * Example 2:
 * Input: nums = [0,1,0,3,2,3]
 * Output: 4
 *
 * Example 3:
 * Input: nums = [7,7,7,7,7,7,7]
 * Output: 1
 *
 * Constraints:
 * 1 <= nums.length <= 2500
 * -10000 <= nums[i] <= 10000
 */
public class P300_LongestIncreasingSubsequence {
    /**
     * AC: Runtime 37 ms Beats 74.02%, Memory 43.84 MB Beats 89.21%
     * 一开始轻视这个问题了。做P1143_LongestCommonSubsequence的过程中，有一部分是解决这个问题，做1143时以为这部分写对了，后来看思路不对。
     * 然后自己想思路，想错了2-3次，最后一次思路对了。
     * 自底向上DP写法。 但是我觉得我写的办法有点暴力【O(n^2)】，不过AC结果还不错。
     */
    public int lengthOfLIS(int[] nums) {
        int res = 1;
        int[] numLIS = new int[nums.length];    //对于i，记录的是从i开始（包含i），最大的递增子串长度
        numLIS[numLIS.length - 1] = 1;
        for (int i = nums.length - 2; i >= 0; i--) {
            int tmp = 1;
            if(nums[i] == nums[i + 1]){   //当前等于下一个
                numLIS[i] = numLIS[i + 1];
            }else{
                //当前和下一个不等，就一个个检查直到最后（！！！我之前错的思路会分大于当前、小于当前两种情况，然后向后查找到第一个小于/大于当前的，结果各种新的wrong case）
                //往后查看所有>当前值的，在他们的numLIS[j]基础上+1。过程中记录最大的，保存到当前的numLIS[i]上。
                for (int j = i + 1; j < nums.length; j++) {
                    if(nums[j] > nums[i]){
                        tmp = Math.max(numLIS[j] + 1, tmp);
                    }
                }
            }
            numLIS[i] = tmp;
            res = Math.max(res, tmp);
        }
        return res;
    }

    public static void main(String[] args) {
        P300_LongestIncreasingSubsequence p = new P300_LongestIncreasingSubsequence();
        System.out.println(p.lengthOfLIS(new int[]{10,9,2,5,3,7,101,18}));  //4  [2 3 7 10]
        System.out.println(p.lengthOfLIS(new int[]{0,1,0,3,2,3}));  //4 [0 1 2 3]
        System.out.println(p.lengthOfLIS(new int[]{7,7,7,7,7,7}));  //1
        System.out.println(p.lengthOfLIS(new int[]{4,10,4,3,8,9}));  //3 [3 8 9]
    }
}
