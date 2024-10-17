package LeetCode.round3;

import java.util.ArrayList;
import java.util.List;

/**
 * 241016 medium
 * https://leetcode.com/problems/maximum-sum-circular-subarray/description/?envType=study-plan-v2&envId=top-interview-150
 * 找循环数组里子数组的最大sum。P053_MaximumSubarray是此问题的简化版，非循环数组。
 *
 * Given a circular integer array nums of length n, return the maximum possible sum of a non-empty subarray of nums.
 * A circular array means the end of the array connects to the beginning of the array. Formally, the next element of nums[i] is nums[(i + 1) % n] and the previous element of nums[i] is nums[(i - 1 + n) % n].
 * A subarray may only include each element of the fixed buffer nums at most once.
 * Formally, for a subarray nums[i], nums[i + 1], ..., nums[j], there does not exist i <= k1, k2 <= j with k1 % n == k2 % n.
 *
 * Example 1:
 * Input: nums = [1,-2,3,-2]
 * Output: 3
 * Explanation: Subarray [3] has maximum sum 3.
 *
 * Example 2:
 * Input: nums = [5,-3,5]
 * Output: 10
 * Explanation: Subarray [5,5] has maximum sum 5 + 5 = 10.
 *
 * Example 3:
 * Input: nums = [-3,-2,-3]
 * Output: -2
 * Explanation: Subarray [-2] has maximum sum -2.
 *
 * Constraints:
 * n == nums.length
 * 1 <= n <= 3 * 10^4
 * -3 * 10^4 <= nums[i] <= 3 * 10^4
 */
public class P918_MaximumSumCircularSubarray {


    /**
     * 参考的答案：https://leetcode.com/problems/maximum-sum-circular-subarray/solutions/178422/one-pass
     * 【--思路：反向思维--】
     * AC： Runtime 9 ms Beats 10.01%。 Memory 48.88 MB Beats 87.20%
     */
    public int maxSubarraySumCircular(int[] nums) {
        //case1: max在数组中间
        int sum1 = maxSubarraySumNonCircular(nums, 0, nums.length);

        //case2: max在首位相连部分。则运用公式： maxSum = totalSum - minSum。！！！！这个公式我应该想不出来！！！！！
        //注意有个edge case。
        int totalSum =0;
        for (int i = 0; i < nums.length; i++) {
            totalSum += nums[i];
        }
        int minSum = minSubarraySumNonCircular(nums);
        int sum2 = totalSum - minSum;
        if(sum2 == 0)   //edge case：全部都是负数时，sum2会计算得0
            sum2 = Integer.MIN_VALUE;

        //返回sum1 sum2更大的即结果。
        return Math.max(sum1, sum2);
    }

    private int minSubarraySumNonCircular(int nums[]){
        int res = nums[0], sum = 0;
        for (int i = 0; i < nums.length; i++) {
            int n = nums[i];
            sum += n;
            res = Math.min(res, Math.min(n, sum));
            if (sum > 0) {
                sum = 0;
            }
        }
        return res;
    }

    //------------------------------------------------------------------//

    /**
     * 本题主要的复杂点在于数组是【循环】的。而非循环版本，求解是比较容易的。
     * 思路：将数组扩展成2倍大小，然后从第一个元素开始，依次看原数组长度的subarray里非循环版本最大的sum，过程中遇到的最大sum即最终结果。
     * 时间复杂度： O(n^2)。空间复杂度： O(n)
     * 问题：会超时，因为确实很多重复计算，偏暴力。这个思路也想了一些时间才想出来的。
     * @param nums
     * @return
     */
    public int maxSubarraySumCircular_timeout(int[] nums) {
        int res = nums[0];
        //扩展原数组到2倍
        int[] newNums = new int[nums.length * 2];
        int len = nums.length;
        for (int i = 0; i < nums.length; i++) {
            newNums[i] = nums[i];
            newNums[i + len] = nums[i];
        }

        //对n个sub array,依次调用非循环数组版本的max subarray sum.
        for (int i = 0; i < len; i++) {
            int tmp = maxSubarraySumNonCircular(newNums, i, i + len);
            res = Math.max(res, tmp);
        }
        return res;
    }

    //求max subarray sum【非循环数组版本】
    private int maxSubarraySumNonCircular(int[] nums, int start, int end){
        int res = nums[0], sum = 0;
        for (int i = start; i < end; i++) {
            int n = nums[i];
            sum += n;
            res = Math.max(res, Math.max(n, sum));
            if (sum < 0) {
                sum = 0;
            }
        }
        return res;
    }

    //------------------------------------------------------------------//

    /**
     * 错误思路：以负数为分割。分隔为多个subarray。
     * 计算每个subarray的sum，取其中最大的。第一个subarray、最后一个subArray如果能连接起来，要特殊处理。
     * ！！！！负数做分割的思路不对 ！！！！
     * 用例：{3,-1,2,-1}， expect: 4, actual: 3
     *
     * 而且写的过程中也是debug着写的，问题较多，不能bug free。
     */
    public int maxSubarraySumCircular_wrong(int[] nums) {
        int res = Integer.MIN_VALUE;

        //找到所有不包含负数的子数组
        List<int[]> pairs = new ArrayList<>();
        int start = -1, end = -1;
        for (int i = 0; i < nums.length; i++) {
            int n = nums[i];
            res = Math.max(res, n);
            if(n >= 0){
                if(start == -1){    //！！这部分错误较多，开始写简单了。
                    start = i;
                    end = i;
                }else {
                    end = i;
                }
                if(i == nums.length - 1){      //！！！不能少，否则最后一组pair就丢了。
                    pairs.add(new int[]{start, end});
                }
            }else {
                if(start >= 0 && end >= 0) {    //！！不能少，否则全负数的情况，会错误生成[-1, -1] pair。
                    pairs.add(new int[]{start, end});
                }
                start = -1;
                end = -1;
            }
        }

        //遍历所有子数组，求他们的和。
        for(int[] pair: pairs){
            int sum = 0;
            for (int i = pair[0]; i <= pair[1]; i++) {  //！！！这里一开始逻辑也写错了
                sum += nums[i];
            }
            res = Math.max(res, sum);
        }

        //特殊情况：如果list头数组从0开始，list最后一个数组以nums里最后一个元素结束，需要将头尾的sum加和。
        if(pairs.size() > 1){
            int sum = 0;
            int[] startPair = pairs.get(0), endPair = pairs.get(pairs.size() - 1);
            if(startPair[0] == 0 && endPair[1] == nums.length - 1){
                for (int i = startPair[0]; i <= startPair[1]; i++) {
                    sum += nums[i];
                }
                for (int i = endPair[0]; i <= endPair[1]; i++) {
                    sum += nums[i];
                }
                res = Math.max(res, sum);
            }
        }

        return res;
    }

    public static void main(String[] args) {
        P918_MaximumSumCircularSubarray p = new P918_MaximumSumCircularSubarray();
        System.out.println(p.maxSubarraySumCircular(new int[]{1,-2,3,-2})); //3
        System.out.println(p.maxSubarraySumCircular(new int[]{5,-2,5,3})); //13
        System.out.println(p.maxSubarraySumCircular(new int[]{-3,-2,-3})); //-2
        System.out.println(p.maxSubarraySumCircular(new int[]{3,-1,2,-1})); //4
        System.out.println(p.maxSubarraySumCircular(new int[]{-1,9,-30,-30, 8,-2})); //14
    }
}
