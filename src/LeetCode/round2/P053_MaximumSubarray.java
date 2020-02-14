package LeetCode.round2;

/**
200213
给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 示例:
 输入: [-2,1,-3,4,-1,2,1,-5,4],
 输出: 6
 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。

 进阶:
 如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。
 */
public class P053_MaximumSubarray {

    // O(n)的方法，还算顺利
    // ！！！！ 一个bug： if(sum > res) res = sum; if(sum < 0) sum = 0; 顺序不能错，否则在{-1}下返回0.
    public int maxSubArray(int[] nums) {
        int sum = 0, res = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] < 0){
                sum += nums[i];
                if(sum > res)
                    res = sum;
                if(sum < 0)
                    sum = 0;
            }else {
                sum += nums[i];
                if(res < sum)
                    res = sum;
            }
        }
        return res;
    }


}
