package LeetCode.round3;

import java.util.Arrays;

/**
 * 240815 medium
 * 给定一个整数数组 nums，返回一个数组 answer，使得 answer[i] 等于 nums 中除 nums[i] 之外的所有元素的乘积。
 * 您必须编写一个在 O(n) 时间内运行且不使用除法运算的算法。
 *
 * Example 1:
 * Input: nums = [1,2,3,4]
 * Output: [24,12,8,6]
 *
 * Example 2:
 * Input: nums = [-1,1,0,-3,3]
 * Output: [0,0,9,0,0]
 *
 * Constraints:
 * 2 <= nums.length <= 105
 * -30 <= nums[i] <= 30
 *
 * Follow up: Can you solve the problem in O(1) extra space complexity? (The output array does not count as extra space for space complexity analysis.)
 */
public class P238_ProductOfArrayExceptSelf {
    /**
     * 自己还是想不出来这个思路。看的16年的笔记。
     * AC: Runtime 229ms Beats 7.72%. Memory 55.80MB Beats 33.60%
     */
    public int[] productExceptSelf(int[] nums) {
        int[] leftProduct = new int[nums.length];   //leftProduct[i]保存的是nums[i]左边元素的积
        int[] rightProduct = new int[nums.length];   //rightProduct[i]保存的是nums[i]右边元素的积

        //两个数组分别赋值
        leftProduct[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            leftProduct[i] = leftProduct[i - 1] * nums[i - 1];
        }
        rightProduct[rightProduct.length - 1] = 1;
        for (int i = rightProduct.length - 2; i >= 0 ; i--) {
            rightProduct[i] = rightProduct[i + 1] * nums[i + 1];
        }

        //两个数组元素相乘，就是最终结果
        //其实可以优化到只用一个数组。没必要保留两个。
        for (int i = 0; i < leftProduct.length; i++) {
            leftProduct[i] = leftProduct[i] * rightProduct[i];
        }
        return leftProduct;
    }

    public static void main(String[] args) {
        P238_ProductOfArrayExceptSelf p = new P238_ProductOfArrayExceptSelf();
        System.out.println(Arrays.toString(p.productExceptSelf(new int[]{1,2,3,4})));   //[24,12,8,6]
        System.out.println(Arrays.toString(p.productExceptSelf(new int[]{-1,1,0,-3,3})));   //[0,0,9,0,0]
    }
}
