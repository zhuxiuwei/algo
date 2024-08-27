package LeetCode.round3;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Given an integer array nums, find a subarray that has the largest product, and return the product.
 * The test cases are generated so that the answer will fit in a 32-bit integer.
 *
 * Example 1:
 * Input: nums = [2,3,-2,4]
 * Output: 6
 * Explanation: [2,3] has the largest product 6.
 *
 * Example 2:
 * Input: nums = [-2,0,-1]
 * Output: 0
 * Explanation: The result cannot be 2, because [-2,-1] is not a subarray.
 *
 * Constraints:
 * 1 <= nums.length <= 20000
 * -10 <= nums[i] <= 10
 * The product of any subarray of nums is guaranteed to fit in a 32-bit integer.
 */
public class P152_MaximumProductSubarray {

    /**
     * 思路：
     * 1、因为有0就会导致成绩清零，先找出所有的0，围绕0的左右，分割出多个子数组，分别计算每个子数组最大乘积。
     * 2、在每个子数组内，统计负数的个数。
     *      如果有1个负数，分别计算左右两边的乘积，取最大的（包括这个负数本身）
     *      如果是偶数个，就全部乘起来就好（都是整数，肯定越乘越大）
     *      如果是奇数(>=3)个，就需要看最左、最右两个负数隔离出来的左右两部分区间的乘积，取大的。【俩区间：[start, 倒数第1个负数)、(第一个负数，end]
     * 4、
     * AC: Runtime 1ms Beats 96.83%, Memory 45.08 MB Beats 21.50%
     * @param nums
     * @return
     */
    public int maxProduct(int[] nums) {
        int res = Integer.MIN_VALUE;
        //先查找共有多少个负数、多少个0
        List<Integer> zeroIdxes = new ArrayList<>();    //保存0的索引位置。第一个元素是-1，第二个元素是nums.length，用于哨兵。
        List<List<Integer>> minusIdxes = new ArrayList<>();     //保存每个0分隔的子数组里，负数的位置idx。
        List<Integer> curMinusIdx = new ArrayList<>();  //当前0分隔的子数组里，负数的位置idx。
        minusIdxes.add(curMinusIdx);
        zeroIdxes.add(-1);  //哨兵
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] == 0) {
                zeroIdxes.add(i);
                curMinusIdx = new ArrayList<>();
                minusIdxes.add(curMinusIdx);
            }
            if(nums[i] < 0) {
                curMinusIdx.add(i);
            }
        }
        zeroIdxes.add(nums.length);  //哨兵
        for (int i = 0; i < zeroIdxes.size() - 1; i++) {
            int start = zeroIdxes.get(i) + 1;
            int end = zeroIdxes.get(i + 1) - 1;
            res = Math.max(getSubArrayProduct(nums, start, end, minusIdxes.get(i)), res);
        }
        if(zeroIdxes.size() > 2 && res < 0)     //如果前面所有乘积结果都是负的，但是数组里有0，那么返回0。！！！注意因为有哨兵，所以判断：zeroIdxes.size() > 2 而不是 >0
            res = 0;
        return res;
    }

    /**
     *
     * @param nums
     * @param start
     * @param end
     * @param minusIdx
     * @return
     */
    private int getSubArrayProduct(int[] nums, int start, int end, List<Integer> minusIdx){
        if(start <= end) {
            int minusSize = minusIdx.size();
            if (minusSize == 1) {
                //分别计算左右两边的结果，返回大的
                int res1 = doGetSubArrayProduct(nums, start, minusIdx.get(0) - 1);
                int res2 = doGetSubArrayProduct(nums, minusIdx.get(0) + 1, end);
                return Math.max(nums[minusIdx.get(0)], Math.max(res1, res2)); //唯一的负数也要参与比较。 ！！！注意取值是：nums[minusIdx.get(0)]
            }
            if (minusSize % 2 == 0) {
                //偶数个，那么全部计算乘积即可
                return doGetSubArrayProduct(nums, start, end);
            } else {
                //奇数个，分别计算囊括第一个奇数、最后一个奇数区间的。（2个区间）
                int leftStart = start, leftEnd = minusIdx.get(minusIdx.size() - 1) - 1;  //[start, 倒数第1个负数)之间
                int rightStart = minusIdx.get(0) + 1, rightEnd = end;   //(第一个负数，end]之间
                int res1 = doGetSubArrayProduct(nums, leftStart, leftEnd);
                int res2 = doGetSubArrayProduct(nums, rightStart, rightEnd);
                return Math.max(res1, res2);
            }
        }
        return Integer.MIN_VALUE;
    }

    private int doGetSubArrayProduct(int[] nums, int start, int end){
        if(start <= end) {  //！！！需要加这个判断，否则可能返回错误结果。
            int res = 1;
            for (int i = start; i <= end; i++) {
                res *= nums[i];
            }
            return res;
        }
        return Integer.MIN_VALUE;
    }

    public static void main(String[] args) {
        P152_MaximumProductSubarray p = new P152_MaximumProductSubarray();
        System.out.println(p.maxProduct(new int[]{2,3,-2,4}));  //6
        System.out.println(p.maxProduct(new int[]{-2,0,-1}));   //0
        System.out.println(p.maxProduct(new int[]{-2}));   //-2
    }
}
